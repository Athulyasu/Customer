package com.customer.controller;

import com.customer.DTO.MonthlyUsageDTO;
import com.customer.exceptionHandler.ApiRequestException;
import com.customer.model.Customer;
import com.customer.model.Telephonebill;
import com.customer.service.CustomerService;
import com.customer.service.MonthlyUsageService;
import com.customer.service.TelephonebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping(path = "/v1/customer")
@RestController
public class UsageController {

    @Autowired
    TelephonebillService telephonebillService;
    @Autowired CustomerService customerservice;
    @Autowired
    MonthlyUsageService monthlyUsageService;
    @GetMapping(path = "/getMonthlyUsage")
    public ResponseEntity<List<MonthlyUsageDTO>> getCustomer(){
        List<MonthlyUsageDTO> monthlyDto=monthlyUsageService.getMonthlydata();
        return new ResponseEntity<>(monthlyDto, HttpStatus.OK);
    }

}