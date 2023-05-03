package com.customer.controller;

import com.customer.DTO.TelephonebillDTO;
import com.customer.ValidationUtility.CustomerValaidation;
import com.customer.exceptionHandler.ApiRequestException;
import com.customer.model.Customer;
import com.customer.model.Telephonebill;
import com.customer.service.EntityConverter;
import com.customer.service.CustomerService;
import com.customer.service.TelephonebillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;

@RequestMapping(path = "/v1/customer")
@RestController
public class PhoneBillController {
    @Autowired
    TelephonebillService telephonebillService;
    @Autowired
    CustomerService customerservice;
    @Autowired
    CustomerValaidation customervalidation;

    @PostMapping(value = "/saveTelephoneBill")
    public ResponseEntity<TelephonebillDTO> saveTelephoneBill(
            @Valid @RequestBody TelephonebillDTO telephonebillData) {
        TelephonebillDTO saveDtoData = new TelephonebillDTO();
        if(customervalidation.validateBill(telephonebillData)) {
            if(telephonebillData.getCustomerId()==null) throw new ApiRequestException("Customer ID not found");
            EntityConverter entityConverter =new EntityConverter();
            Optional<Customer> customerData=customerservice.findById(telephonebillData.getCustomerId());
            if(customerData.isPresent()){
                Telephonebill telephonebill= entityConverter.convertToTelephoneBillEntityDomain(telephonebillData,customerData.get());
                saveDtoData=telephonebillService.saveOrUpdate(telephonebill);
                return new ResponseEntity<>(saveDtoData, HttpStatus.OK);
            }
//            else{
//                return new ResponseEntity<>(saveDtoData,"Invalid Customer ID!", HttpStatus.OK);
//            }
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(saveDtoData, HttpStatus.OK);
    }
    @PatchMapping(value = "/updateTelephoneBill")
    public ResponseEntity<TelephonebillDTO> updateCustomerData(
            @RequestParam(name = "bill_id") String bill_id,
            @RequestParam(name = "usageInMb") String usageInMb) {
        Optional<Telephonebill> TelephonebillData=telephonebillService.findById(Integer.parseInt(bill_id));
        TelephonebillDTO updatedDtoData = new TelephonebillDTO();

        if(TelephonebillData.isPresent()) {
            Telephonebill telephonebillUpdatedData=TelephonebillData.get();
            telephonebillUpdatedData.setUsageInMb(Double.parseDouble(usageInMb));
            updatedDtoData=telephonebillService.saveOrUpdate(telephonebillUpdatedData);
            return new ResponseEntity<>(updatedDtoData, HttpStatus.OK);        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping(path = "/getCustomerBill")
    public ResponseEntity<Page<Telephonebill>> getAllCustomer(@RequestParam(name = "name") String name,
                      @RequestParam(name = "bill_date") String bill_date,
                      final Sort.Direction order,
                      final int page,
                      final int size){
        PageRequest pageRequest = PageRequest.of(page, size, order,"billDate");
        Page<Telephonebill> customer=telephonebillService.findCustomerBill(name,bill_date,pageRequest);
        System.out.println("SIZE>>"+customer.getTotalElements());
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
