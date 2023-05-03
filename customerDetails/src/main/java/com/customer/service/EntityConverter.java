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
    public CustomerDTO convertToCustomerDtoDomain(Customer Customer)
    {
        CustomerDTO CustomerDTO=new CustomerDTO();
        CustomerDTO.setName(Customer.getName());
        CustomerDTO.setPermanentAddress(Customer.getPermanentAddress());
        CustomerDTO.setCommunicationAddress(Customer.getCommunicationAddress());
        CustomerDTO.setCity(Customer.getCity());
        CustomerDTO.setDistrict(Customer.getDistrict());
        CustomerDTO.setDob(Customer.getDob());
        CustomerDTO.setState(Customer.getState());
        CustomerDTO.setPhoneNo(Customer.getPhoneNo());
        CustomerDTO.setMobileNo(Customer.getMobileNo());
        CustomerDTO.setPin(Customer.getPin());
        CustomerDTO.setCountry(Customer.getCountry());
        CustomerDTO.setCustomerId(Customer.getCustomerId());
        return CustomerDTO;
    }
    public TelephonebillDTO convertToBillDtoDomain(Telephonebill Telephonebilldata)
    {
        TelephonebillDTO telephonebillData=new TelephonebillDTO();
        System.out.println("Telephonebilldata"+Telephonebilldata.getUsageInMb());
        telephonebillData.setCustomerId(Telephonebilldata.getCustomer().getCustomerId());
        telephonebillData.setUsageInMb(Telephonebilldata.getUsageInMb());
        telephonebillData.setBillDate(Telephonebilldata.getBillDate());
        telephonebillData.setBillId(Telephonebilldata.getBillId());
        return telephonebillData;
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
