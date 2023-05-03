package com.customer.service;

import com.customer.DTO.TelephonebillDTO;
import com.customer.model.Telephonebill;
import com.customer.repository.TelephonebillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TelephonebillService {
    @Autowired
    TelephonebillRepository telephonebillRepository;

    public TelephonebillDTO saveOrUpdate(Telephonebill TelephonebillData) {

        EntityConverter entityConverter =new EntityConverter();

        Telephonebill savedBill=telephonebillRepository.save(TelephonebillData);
        // Convert User JPA entity to UserDto
        TelephonebillDTO TelephonebillDtoData = entityConverter.convertToDtoDomain(savedBill);

        return TelephonebillDtoData;
    }
    public Optional<Telephonebill> findById(int bill_id) {
        return telephonebillRepository.findById(bill_id);
    }
    public Page<Telephonebill> findCustomerBill(String customer_name, String bill_date,Pageable pageRequest) {
        Page<Telephonebill> customerlist;
        if((customer_name.isEmpty() || customer_name.equals("null"))&& (bill_date.isEmpty()|| bill_date.equals("null")))
        {
            customerlist= telephonebillRepository.findAllBill(pageRequest);
        }
        else
        {
            System.out.println("billDate"+bill_date);
            customerlist= telephonebillRepository.findBillByNameAndDate(customer_name,bill_date,pageRequest);
        }
        return customerlist;
    }

}
