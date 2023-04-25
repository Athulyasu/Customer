package com.customer.service;

import com.customer.repository.CustomerRepository;
import com.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerrepository;

    public void saveOrUpdate(Customer customerdata) {
        customerrepository.save(customerdata);
    }




}
