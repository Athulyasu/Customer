package com.customer.controller;

import com.customer.DTO.CustomerPojo;
import com.customer.ValidationUtility.CustomerValaidation;
import com.customer.exceptionHandler.ApiRequestException;
import com.customer.model.Customer;
import com.customer.service.CustomerConverter;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RequestMapping(path = "/v1/customer")
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class CustomerController {

    @Autowired
    CustomerService customerservice;
    @Autowired
    CustomerValaidation customervalidation;
    @PostMapping(value = "/saveCustomer")
    public ResponseEntity<String> saveCustomerData(
            final @RequestBody CustomerPojo customerDataPojo) {
        if(customervalidation.validate(customerDataPojo)) {
            CustomerConverter customerConverter=new CustomerConverter();
            Customer customerData=customerConverter.convertToEntityDomain(customerDataPojo);
            customerservice.saveOrUpdate(customerData);
            if(customerDataPojo==null) throw new ApiRequestException("customerDataPojo not found");
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PatchMapping(value = "/updateCustomer")
    public ResponseEntity<String> updateCustomerData(
            @RequestParam(name = "customer_id") String customer_id,
            @RequestParam(name = "name") String name) {
        Optional<Customer> customerData=customerservice.finById(UUID.fromString(customer_id));
        if(customerData.isPresent()) {
            Customer customerUpdatedData=customerData.get();
            customerUpdatedData.setName(name);
            customerservice.saveOrUpdate(customerUpdatedData);
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
