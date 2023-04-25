package com.customer.controller;

import com.customer.ValidationUtility.CustomerValaidation;
import com.customer.model.Customer;
import com.customer.DTO.CustomerPojo;
import com.customer.service.CustomerConverter;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CheckoutController {
    @Autowired
    CustomerService customerservice;
    @Autowired
    CustomerValaidation customervalidation;

    @PostMapping("/customer/checkout")
    private String checkOutCustomer(@Valid @RequestBody CustomerPojo customerDataPojo) throws Exception
    {
        try {

            if(customervalidation.validate(customerDataPojo)) {
                CustomerConverter customerConverter=new CustomerConverter();
                Customer customerData=customerConverter.convertToEntityDomain(customerDataPojo);
                 customerservice.saveOrUpdate(customerData);
                 return "SUCCESS";
            }
            else {
                //handle error
                return "ERROR";
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

}
