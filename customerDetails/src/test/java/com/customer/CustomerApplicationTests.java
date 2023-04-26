package com.customer;
import com.customer.ValidationUtility.CustomerValaidation;
import com.customer.DTO.CustomerPojo;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CustomerValaidation.class)
class CustomerApplicationTests {
	//	@Autowired
	@InjectMocks
	CustomerValaidation customervalidation;
	@Mock
	CustomerRepository customerRepository;

	//Mock the service which is to be tested (Can't be a interface)
	@InjectMocks
	CustomerService customerService;

	@Test
	void testValidate()  {
		CustomerPojo customerPojo = setCustomerData();
//		assertThrows(IllegalArgumentException.class, () -> customervalidation.validate(customerPojo),
//				"Error in customer data ");
//		assertFalse(customervalidation.validate(customerPojo));

		customerPojo.setName("sdfsf");
		customerPojo.setDistrict("TVM");
		assertTrue(customervalidation.validate(customerPojo));

	}

	@Test
	public void testValidateOptional()  {
		Customer customerPojo = buildCustomerData();
		when(customerRepository.findById(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68614"))).
				thenReturn(Optional.of(customerPojo));
		assertNotNull(customerService.finById(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68614")));
	}

	private CustomerPojo setCustomerData() {
		CustomerPojo customerPojo = new CustomerPojo();
		Date date=new Date();
		customerPojo.setName("Anu A");
		customerPojo.setPermanentAddress("TEST ADDRESS");
		customerPojo.setCommunicationAddress("DEMO ADDRESS");
		customerPojo.setCity("ATTINGAL");
		customerPojo.setDistrict("TVM");
		customerPojo.setDob(date);
		customerPojo.setState("KERALA");
		customerPojo.setPhoneNo(98745615L);
		customerPojo.setMobileNo(8512346310L);
		customerPojo.setPin(611295);
		customerPojo.setCountry("INDIA");
		return customerPojo;
	}

	private Customer buildCustomerData() {
		Customer Customer = new Customer();
		Date date=new Date();
		Customer.setCustomerId(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68614"));
		Customer.setName("Anu A");
		Customer.setPermanentAddress("TEST ADDRESS");
		Customer.setCommunicationAddress("DEMO ADDRESS");
		Customer.setCity("ATTINGAL");
		Customer.setDistrict("TVM");
		Customer.setDob(date);
		Customer.setState("KERALA");
		Customer.setPhoneNo(98745615L);
		Customer.setMobileNo(8512346310L);
		Customer.setPin(611295);
		Customer.setCountry("INDIA");
		return Customer;
	}
}