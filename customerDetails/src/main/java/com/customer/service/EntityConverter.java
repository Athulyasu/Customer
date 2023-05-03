package com.customer.service;


import com.customer.DTO.TelephonebillDTO;
import com.customer.model.Customer;
import com.customer.DTO.CustomerDTO;
import com.customer.model.Telephonebill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityConverter {
    @Autowired
    CustomerService customerservice;
    public Customer convertToEntityDomain(CustomerDTO customerPojo)
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

    public Telephonebill convertToTelephoneBillEntityDomain(TelephonebillDTO telephonebillData,Customer customerData)
    {
        Telephonebill telephonebill=new Telephonebill();
        telephonebill.setCustomer(customerData);
        telephonebill.setUsageInMb(telephonebillData.getUsageInMb());
        telephonebill.setBillDate(telephonebillData.getBillDate());
        return telephonebill;
    }
}
