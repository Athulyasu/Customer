package com.customer;
import com.customer.ValidationUtility.CustomerValaidation;
import com.customer.DTO.CustomerDTO;
import com.customer.model.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;
import org.hibernate.validator.internal.util.Contracts;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
	//	@Mock
//	CustomerService customerService;
	@Test
	void testValidate()  {
		CustomerDTO customerPojo = setCustomerData();
		//check the function with incorrect data
//		assertThrows(IllegalArgumentException.class, () -> customervalidation.validate(customerPojo),
//				"Error in customer data ");
//		assertFalse(customervalidation.validate(customerPojo));

		customerPojo.setName("TEST");
		customerPojo.setDistrict("TVM");
		assertTrue(customervalidation.validate(customerPojo));

	}

	@Test
	public void testValidateSave()  {
		when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());
		customerService.saveOrUpdate(buildCustomerData());
		verify(customerRepository, times(1)).save(any(Customer.class));
	}
	@Test
	public void testOptional()  {
		Customer customerPojo = buildCustomerData();

		when(customerRepository.findById(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68614"))).
				thenReturn(Optional.of(customerPojo));
		assertNotNull(customerService.findById(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68614")));

		when(customerRepository.findById(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a6861"))).
				thenReturn(null);
		assertNull(customerService.findById(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a6861")));
	}

	@Test
	public void testDelete()  {

		//check existing id false
		when(customerRepository.existsById(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68610"))).
				thenReturn(false);
		assertThrows(Exception.class, () -> customerService.delete(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68610")),
				"Error in customer id ");
		//check existing id true
		when(customerRepository.existsById(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68614"))).
				thenReturn(true);
		doNothing().when(customerRepository).deleteById(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68614"));

		//verify method calling
		customerService.delete(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68614"));
		verify(customerRepository, times(1)).
				deleteById(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68614"));
//		verify(customerService, times(1)).
//				delete(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68614"));

	}
	@Test
	public void testsearchCustomers()  {

		List<Customer> customer= new ArrayList<Customer>();
		when(customerRepository.searchCustomers("A")).
				thenReturn(Collections.singletonList(buildCustomerData()));
		assertNotNull(customerService.findCustomers("A"));

		when(customerRepository.searchCustomers(anyString())).
				thenReturn(null);
		assertNull(customerRepository.searchCustomers(anyString()));

	}

	private CustomerDTO setCustomerData() {
		CustomerDTO customerPojo = new CustomerDTO();
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
