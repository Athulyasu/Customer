package com.customer;

import com.customer.controller.CustomerValaidation;
import com.customer.pojo.CustomerPojo;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class CustomerTestAppplication {
    @Mock
    CustomerValaidation customervalidation;
    CustomerPojo customerPojo = null;

    @DisplayName("Test Mock Validation")
    @Test
    void testValidate() {
        customerPojo = new CustomerPojo();
        customerPojo=setCustomerData(customerPojo);
        customervalidation.validate(customerPojo);
        //when(customervalidation.validate(any(CustomerPojo.class))).thenCallRealMethod();
       /* assertThrows(Exception.class, () -> {
            customervalidation.validate(customerPojo);
        });*/
       // assertThrows(IllegalArgumentException.class, () -> customervalidation.validate(customerPojo),
              //  "Error in customer data ");
        /*assertThrows(Exception.class, () -> {
            customervalidation.validate(customerPojo);
        });*/
        //customerPojo.setPin(123456);
        //assertTrue(customervalidation.validate(customerPojo));

    }

private CustomerPojo setCustomerData( CustomerPojo customerPojo)
{
    Date date=new Date();
    customerPojo.setName("ANIL");
    customerPojo.setPermanentAddress("TEST ADDRESS123");
    customerPojo.setCommunicationAddress("DEMO ADDRESS");
    customerPojo.setCity("ATTINGAL");
    customerPojo.setDistrict("TVM");
    customerPojo.setDob(date);
    customerPojo.setState("KERALA");
    customerPojo.setPhoneNo(9874569015L);
    customerPojo.setMobileNo(9808563210L);
    customerPojo.setPin(619415);
    customerPojo.setCountry("INDIA");
    return customerPojo;
}

}
