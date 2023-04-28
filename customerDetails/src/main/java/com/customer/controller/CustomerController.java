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
            @Valid @RequestBody CustomerPojo customerDataPojo) {
        if(customervalidation.validate(customerDataPojo)) {
            CustomerConverter customerConverter=new CustomerConverter();
            Customer customerData=customerConverter.convertToEntityDomain(customerDataPojo);
            customerservice.saveOrUpdate(customerData);
            if(customerDataPojo==null) throw new ApiRequestException("customerDataPojo not found");
            return new ResponseEntity<>("Customer successfully Saved!", HttpStatus.OK);
//            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PatchMapping(value = "/updateCustomer")
    public ResponseEntity<String> updateCustomerData(
            @RequestParam(name = "customer_id") String customer_id,
            @RequestParam(name = "name") String name) {
        Optional<Customer> customerData=customerservice.findById(UUID.fromString(customer_id));
        if(customerData.isPresent()) {
            Customer customerUpdatedData=customerData.get();
            customerUpdatedData.setName(name);
            customerservice.saveOrUpdate(customerUpdatedData);
            return new ResponseEntity<>("Customer successfully Updated!", HttpStatus.OK);        
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping(path = "/deleteCustomer")
    public ResponseEntity<String> deleteCustomer(@RequestParam(name = "customer_id") String customer_id){
        customerservice.delete(UUID.fromString(customer_id));
        if(customer_id==null) throw new ApiRequestException("customer_id not found");
        return new ResponseEntity<>("Customer successfully Deleted!", HttpStatus.OK);
    }
    @GetMapping(path = "/getCustomer")
    public ResponseEntity<Customer> getCustomer(@RequestParam(name = "customer_id") String customer_id){
        Customer customerData=customerservice.findById(UUID.fromString(customer_id)).get();
        if(customer_id==null) throw new ApiRequestException("customer_id not found");
        return new ResponseEntity<>(customerData, HttpStatus.OK);
    }
    @GetMapping(path = "/getAllCustomer")
    public ResponseEntity<List<CustomerDTO>> getAllCustomer(@RequestParam(name = "name") String name){
        List<CustomerDTO> customerlist=customerservice.findCustomers(name);
        return new ResponseEntity<>(customerlist, HttpStatus.OK);
    }

}
