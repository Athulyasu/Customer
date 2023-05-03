package com.customer.ValidationUtility;


import com.customer.DTO.CustomerDTO;
import com.customer.DTO.TelephonebillDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static java.lang.String.format;
@Component
public class CustomerValaidation {

    public static Validation<String> notNull = SimpleValidation.from((s) -> s != null, "must not be null.");

    public static Validation<String> moreThan(int size){
        return SimpleValidation.from((s) -> s.length() >= size, format("must be equal to %s .", size));
    }

    public static Validation<String> lessThan(int size){
        return SimpleValidation.from((s) -> s.length() <= size, format("must be equal to %s .", size));
    }
    public static Validation<String> between(int minSize, int maxSize){
        return moreThan(minSize).and(lessThan(maxSize));
    }
    public static Validation<String> isNumeric(String c){
        return SimpleValidation.from((s) -> StringUtils.isNumeric(s), format("must not be contain %s", c));
    } //only accept number
    public static Validation<String> isAlpha(String c){
        return SimpleValidation.from((s) -> StringUtils.isAlpha(s), format("must not be contain %s", c));
    }
    public static Validation<String> isAlphaSpace(String c){
        return SimpleValidation.from((s) -> StringUtils.isAlphaSpace(s), format("must not be contain %s", c));
    }


    public boolean validate(CustomerDTO customerDataPojo) {
        System.out.println("VALIDATE : " + customerDataPojo.getName());
        notNull.and(isAlphaSpace(customerDataPojo.getName())).test(customerDataPojo.getName()).throwIfInvalid("firstname");
        notNull.test(customerDataPojo.getPermanentAddress()).throwIfInvalid("Address");
        notNull.and(isNumeric(String.valueOf(customerDataPojo.getMobileNo()))).and(between(10,15)).test(String.valueOf(customerDataPojo.getMobileNo())).throwIfInvalid("mobile No");
        notNull.and(isNumeric(String.valueOf(customerDataPojo.getPin()))).and(between(6,6)).test(String.valueOf(customerDataPojo.getPin())).throwIfInvalid("Pin");
        notNull.and(isAlpha(customerDataPojo.getCity())).test(customerDataPojo.getCity()).throwIfInvalid("CIty");
        notNull.and(isAlpha(customerDataPojo.getCountry())).test(customerDataPojo.getCountry()).throwIfInvalid("Country");
        notNull.and(isAlpha(customerDataPojo.getDistrict())).test(customerDataPojo.getDistrict()).throwIfInvalid("District");
        notNull.and(isAlpha(customerDataPojo.getState())).test(customerDataPojo.getState()).throwIfInvalid("State");
        return true;
    }
    public boolean validateBill(TelephonebillDTO Telephonebill) {
        Objects.requireNonNull(Telephonebill.getCustomerId(), "Customer ID is required");
        Objects.requireNonNull(Telephonebill.getUsageInMb(), "Data Usage is required in MB");
        return true;

    }
}

