package com.customer.service;

import com.customer.DTO.MonthlyUsageDTO;
import com.customer.repository.TelephonebillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
public class MonthlyUsageService {

    @Autowired
    TelephonebillRepository telephonebillRepository;

    public List<MonthlyUsageDTO> getMonthlydata() {

        LocalDate d1 = LocalDate.now();
        LocalDate previous = d1.minusMonths(2); // previous
        LocalDate start = previous.with(firstDayOfMonth());
        LocalDate end = previous.with(lastDayOfMonth());
        String startDate= start.toString();
        String endDate=  end.toString();
        System.out.println("startDate :  " + startDate);
        System.out.println("endDate :  " + endDate);

        List<MonthlyUsageDTO> monthlyDtolist=new ArrayList<MonthlyUsageDTO>();
        List<Object[]> monthlysum=telephonebillRepository.findMonthlyBill(startDate,endDate);
        if(monthlysum.size()!=0) {
            for(Object[] obj:monthlysum) {
                float monthlyMB = Float.parseFloat(obj[1].toString());
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
                MonthlyUsageDTO monthlyDto=new MonthlyUsageDTO();
                monthlyDto.setAmount(amount);
                monthlyDto.setName(obj[0].toString());
                monthlyDto.setCustomerId(UUID.fromString(obj[2].toString()));
                monthlyDto.setFromDate(startDate);
                monthlyDto.setToDate(endDate);
                monthlyDtolist.add(monthlyDto);
            }
        }
        return monthlyDtolist;
    }
}
