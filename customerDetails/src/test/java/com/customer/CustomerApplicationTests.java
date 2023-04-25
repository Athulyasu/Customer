package com.customer;
import com.customer.ValidationUtility.CustomerValaidation;
import com.customer.DTO.CustomerPojo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CustomerValaidation.class)
class CustomerApplicationTests {
	@Autowired
	CustomerValaidation customervalidation;
	@Test
	void testValidate()  {
		CustomerPojo customerPojo = setCustomerData();
		assertThrows(IllegalArgumentException.class, () -> customervalidation.validate(customerPojo),
		 "Error in customer data ");

		assertFalse(customervalidation.validate(customerPojo));

		customerPojo.setName("sdfsf");
		customerPojo.setDistrict("TVM");
		assertTrue(customervalidation.validate(customerPojo));

	}

	private CustomerPojo setCustomerData()
	{
		CustomerPojo customerPojo = new CustomerPojo();
		Date date=new Date();
		customerPojo.setName("Anu");
		customerPojo.setPermanentAddress("TEST ADDRESS");
		customerPojo.setCommunicationAddress("DEMO ADDRESS");
		customerPojo.setCity("ATTINGAL");
		customerPojo.setDistrict("TVM");
		customerPojo.setDob(date);
		customerPojo.setState("KERALA");
		customerPojo.setPhoneNo(9874569015L);
		customerPojo.setMobileNo(9808563210L);
		customerPojo.setPin(611295);
		customerPojo.setCountry("INDIA");
		return customerPojo;
	}
}
