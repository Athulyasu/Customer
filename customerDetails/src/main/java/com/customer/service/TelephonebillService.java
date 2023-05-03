package com.customer.service;

import com.customer.model.Telephonebill;
import com.customer.repository.TelephonebillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TelephonebillService {
    @Autowired
    TelephonebillRepository telephonebillRepository;

    public void saveOrUpdate(Telephonebill TelephonebillData) {
        telephonebillRepository.save(TelephonebillData);
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
