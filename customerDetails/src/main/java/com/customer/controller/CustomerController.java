package com.customer.controller;

import com.customer.DTO.CustomerDTO;
import com.customer.DTO.TelephonebillDTO;
import com.customer.ValidationUtility.CustomerValaidation;
import com.customer.exceptionHandler.ApiRequestException;
import com.customer.model.Customer;
import com.customer.service.EntityConverter;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(path = "/v1/customer")
@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:8080")
public class CustomerController {

    @Autowired
    CustomerService customerservice;
    @Autowired
    CustomerValaidation customervalidation;
    @PostMapping(value = "/saveCustomer")
    public ResponseEntity<CustomerDTO> saveCustomerData(
            @Valid @RequestBody CustomerDTO customerDataPojo) {
        CustomerDTO savedDtoData = new CustomerDTO();
        if(customervalidation.validate(customerDataPojo)) {
            EntityConverter entityConverter =new EntityConverter();
            Customer customerData= entityConverter.convertToEntityDomain(customerDataPojo);
            savedDtoData=customerservice.saveOrUpdate(customerData);
            if(customerDataPojo==null) throw new ApiRequestException("customerDataPojo not found");
            return new ResponseEntity<>(savedDtoData, HttpStatus.OK);
//            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PatchMapping(value = "/updateCustomer")
    public ResponseEntity<CustomerDTO> updateCustomerData(
            @RequestParam(name = "customer_id") String customer_id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "permanentAdd") String permanentAdd) {
        Optional<Customer> customerData=customerservice.findById(UUID.fromString(customer_id));
        CustomerDTO savedDtoData = new CustomerDTO();
        if(customerData.isPresent()) {
            Customer customerUpdatedData=customerData.get();
            customerUpdatedData.setName(name);
            customerUpdatedData.setPermanentAddress(permanentAdd);
            savedDtoData=customerservice.saveOrUpdate(customerUpdatedData);
            return new ResponseEntity<>(savedDtoData, HttpStatus.OK);        }
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
    public ResponseEntity<Page<Customer>> getAllCustomer(@RequestParam(name = "name") String name,
                     final Sort.Direction order,
                     final int page,
                     final int size){
//        List<CustomerDTO> customerlist=customerservice.findCustomers(name);
        PageRequest pageRequest = PageRequest.of(page, size, order,"dob");
        Page<Customer> customerlist=customerservice.findCustomers(name, pageRequest);
        return new ResponseEntity<>(customerlist, HttpStatus.OK);
    }

}
