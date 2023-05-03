package com.customer.service;

import com.customer.repository.CustomerRepository;
import com.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.customer.DTO.CustomerDTO;

import java.util.Optional;
import java.util.UUID;
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
    public Page<Customer> findCustomers(String customer_name,Pageable pageRequest) {
        Page<Customer> customerlist = null;
        if(customer_name.equals(null) || customer_name.equals("null"))
        {
            customerlist= customerrepository.findAllCustomer(pageRequest);
        }
        else
        {
            customerlist= customerrepository.findByName(customer_name,pageRequest);
        }
        System.out.println("size"+customerlist.getTotalElements());
//        return customerlist.stream()
//                .map(CustomerService::buildUserDetails)
//                .collect(Collectors.toList());
        return customerlist;
    }

    public static CustomerDTO buildUserDetails(
            final Customer customer) {
        CustomerDTO CustomerDto = new CustomerDTO();
        CustomerDto.setCustomerId(customer.getCustomerId());
        CustomerDto.setName(customer.getName());
        CustomerDto.setPermanentAddress(customer.getPermanentAddress());
        CustomerDto.setCommunicationAddress(customer.getCommunicationAddress());
        CustomerDto.setCity(customer.getCity());
        CustomerDto.setDistrict(customer.getDistrict());
        CustomerDto.setDob(customer.getDob());
        CustomerDto.setState(customer.getState());
        CustomerDto.setPhoneNo(customer.getPhoneNo());
        CustomerDto.setMobileNo(customer.getMobileNo());
        CustomerDto.setPin(customer.getPin());
        CustomerDto.setCountry(customer.getCountry());
        return CustomerDto;
    }


}
