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
    public List<CustomerDTO> findCustomers(String customer_name) {
        List<Customer> customerlist=new ArrayList<Customer>();
        if(customer_name.equals(null))
        {
            customerlist= customerrepository.findAll();
        }
        else
        {
            customerlist= customerrepository.searchCustomers(customer_name);
        }
        System.out.println("size"+customerlist);
        return customerlist.stream()
                .map(CustomerService::buildUserDetails)
                .collect(Collectors.toList());
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
