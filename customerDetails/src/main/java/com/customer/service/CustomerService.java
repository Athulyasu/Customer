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

    public Optional<Customer> findById(UUID customer_id) {
        return customerrepository.findById(customer_id);
    }

    public void delete(UUID customer_id) {

        boolean exists= customerrepository.existsById(customer_id);
        if(! exists){throw new IllegalStateException("Customer with id " + customer_id +"does not exists");
        }
        customerrepository.deleteById(customer_id);
    }



}
