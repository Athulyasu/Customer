package com.customer.service;

import com.customer.model.Customer;
import com.customer.DTO.CustomerPojo;

public class CustomerConverter {

    public Customer convertToEntityDomain(CustomerPojo customerPojo)
    {
        Customer customer=new Customer();
        customer.setName(customerPojo.getName());
        customer.setPermanentAddress(customerPojo.getPermanentAddress());
        customer.setCommunicationAddress(customerPojo.getCommunicationAddress());
        customer.setCity(customerPojo.getCity());
        customer.setDistrict(customerPojo.getDistrict());
        customer.setDob(customerPojo.getDob());
        customer.setState(customerPojo.getState());
        customer.setPhoneNo(customerPojo.getPhoneNo());
        customer.setMobileNo(customerPojo.getMobileNo());
        customer.setPin(customerPojo.getPin());
        customer.setCountry(customerPojo.getCountry());
        return customer;
    }

}
