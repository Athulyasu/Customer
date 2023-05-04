package com.customer.service;

import com.customer.DTO.MonthlyUsageDTO;
import com.customer.repository.TelephonebillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
public class MonthlyUsageService {

    @Autowired
    TelephonebillRepository telephonebillRepository;

    public MonthlyUsageDTO getMonthlydata(String customerId) {

        LocalDate d1 = LocalDate.now();
        LocalDate previous = d1.minusMonths(2); // previous
        LocalDate start = previous.with(firstDayOfMonth());
        LocalDate end = previous.with(lastDayOfMonth());
        String startDate= start.toString();
        String endDate=  end.toString();
        System.out.println("startDate :  " + startDate);
        System.out.println("endDate :  " + endDate);

        MonthlyUsageDTO monthlyDto=new MonthlyUsageDTO();
        List<Object[]> monthlysum=telephonebillRepository.findAMonthlyBill(UUID.fromString(customerId),startDate,endDate);
        if(monthlysum.size()!=0) {
            float monthlyMB = Float.parseFloat(monthlysum.get(0)[1].toString());
            float monthlyGB = monthlyMB / 1024;
            float amount = 200;
            if (monthlyGB > 2) {
                float diff = monthlyGB - 2;
                amount = amount + diff;
            }
            if (monthlyGB > 100) {
                amount = amount + 2;
            }
            System.out.println("monthlyGB" + monthlyGB);
            monthlyDto.setAmount(amount);
            monthlyDto.setName(monthlysum.get(0)[0].toString());
            monthlyDto.setCustomerId(UUID.fromString(monthlysum.get(0)[2].toString()));
            monthlyDto.setFromDate(startDate);
            monthlyDto.setToDate(endDate);
        }
        return monthlyDto;
    }
}
