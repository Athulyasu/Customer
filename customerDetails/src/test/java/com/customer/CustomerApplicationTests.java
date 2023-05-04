package com.customer;
import com.customer.DTO.MonthlyUsageDTO;
import com.customer.DTO.TelephonebillDTO;
import com.customer.ValidationUtility.CustomerValaidation;
import com.customer.DTO.CustomerDTO;
import com.customer.model.Customer;
import com.customer.model.Telephonebill;
import com.customer.repository.CustomerRepository;
import com.customer.repository.TelephonebillRepository;
import com.customer.service.CustomerService;
import com.customer.service.MonthlyUsageService;
import com.customer.service.TelephonebillService;
import org.hibernate.validator.internal.util.Contracts;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageImpl;

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
	@Mock
	TelephonebillRepository telephonebillRepository;
	@InjectMocks
	TelephonebillService telephonebillService;
	@InjectMocks
	MonthlyUsageService monthlyUsageService;
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

		PageRequest pageRequest = PageRequest.of(1, 1, Sort.Direction.ASC,"billDate");

		when(customerRepository.findByName("A",pageRequest)).
				thenReturn(buildCustomerPagedData());
		assertNotNull(customerService.findCustomers("A",pageRequest));

		when(customerRepository.findByName("XX",pageRequest)).
				thenReturn(null);
		assertNull(customerRepository.findByName("XX",pageRequest));

	}
	@Test
	public void testValidateBillSave()  {
		when(telephonebillRepository.save(any(Telephonebill.class))).thenReturn(new Telephonebill());

		// create a sample Telephonebill object to save
		Telephonebill telephonebill = setBillData();

		// create a sample TelephonebillDTO object to return
		TelephonebillDTO telephonebillDTO = setBillDtoData();

		// mock the save method of the repository to return the sample Telephonebill object
		when(telephonebillRepository.save(telephonebill)).thenReturn(telephonebill);

		// call the service method and verify the returned TelephonebillDTO object
		TelephonebillDTO result = telephonebillService.saveOrUpdate(telephonebill);
		assertEquals(telephonebillDTO.getBillId(), result.getBillId());
		assertEquals(telephonebillDTO.getUsageInMb(), result.getUsageInMb());

		verify(telephonebillRepository, times(1)).save(any(Telephonebill.class));
	}
	@Test
	public void testBillOptional()  {
		Telephonebill Telephonebill = setBillData();

		when(telephonebillRepository.findById(2)).
				thenReturn(Optional.of(Telephonebill));
		assertNotNull(telephonebillService.findById(2));

		when(telephonebillRepository.findById(1)).
				thenReturn(null);
		assertNull(telephonebillService.findById(1));
	}

	@Test
	public void testsearchCustomerBill()  {

		PageRequest pageRequest = PageRequest.of(1, 1, Sort.Direction.ASC,"billDate");

		when(telephonebillRepository.findBillByNameAndDate("VISHNU","2021-10-10",pageRequest)).
				thenReturn(setBillDataPAge());
		assertNotNull(telephonebillService.findCustomerBill("VISHNU","2021-10-10",pageRequest));


		when(telephonebillRepository.findBillByNameAndDate("XX","2023-10-10",pageRequest)).
				thenReturn(null);
		assertNull(telephonebillService.findCustomerBill("XX","2023-10-10",pageRequest));

	}

	@Test
	public void testGetMonthlydata() {

		when(telephonebillRepository.findAMonthlyBill(any(UUID.class), anyString(), anyString()))
				.thenReturn(Collections.singletonList(new Object[]{"VISHNU", 2048, "e5347911-a71a-4fd0-adcc-64e8a48e0dca"}));

		// Call the getMonthlydata() method with a mock customer ID
		MonthlyUsageDTO result = new MonthlyUsageDTO();
		result = monthlyUsageService.getMonthlydata("e5347911-a71a-4fd0-adcc-64e8a48e0dca");

		// Verify the result
		assertEquals("VISHNU", result.getName());
		assertEquals(UUID.fromString("e5347911-a71a-4fd0-adcc-64e8a48e0dca"), result.getCustomerId());
		assertNotNull(result.getFromDate());
		assertNotNull(result.getAmount());
		assertNotNull(result.getToDate());
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
	private List<CustomerDTO> buildCustomerDtoData() {
		List<CustomerDTO> dtolist=new ArrayList<CustomerDTO>();
		CustomerDTO Customer = new CustomerDTO();
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
		dtolist.add(Customer);
		return dtolist;
	}
	private Telephonebill setBillData() {
		Telephonebill telephonebill = new Telephonebill();
		telephonebill.setBillId(5);
		telephonebill.setUsageInMb(110.9);
		telephonebill.setBillDate(new Date());
		telephonebill.setCustomer(buildCustomerData());
		return telephonebill;
	}
	private TelephonebillDTO setBillDtoData() {
		TelephonebillDTO telephonebillDTO = new TelephonebillDTO();
		telephonebillDTO.setBillId(5);
		telephonebillDTO.setUsageInMb(110.9);
		telephonebillDTO.setBillDate(new Date());
		telephonebillDTO.setCustomerId(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68614"));
		return telephonebillDTO;
	}
	private Page<Telephonebill> setBillDataPAge() {
		Telephonebill Telephonebill = new Telephonebill();
		List<Telephonebill> TelephonebillList = new ArrayList<Telephonebill>();
		int page=0;
		int size=1;
		Sort.Direction order= Sort.Direction.ASC;
		Date date=new Date();
		Customer customer=new Customer();
		customer.setCustomerId(UUID.fromString("0233b6f0-f7e1-4268-8b7f-808ff8a68614"));
		Telephonebill.setCustomer(customer);
		Telephonebill.setBillDate(date);
		Telephonebill.setUsageInMb(110.9);
		TelephonebillList.add(Telephonebill);
		PageRequest pageable = PageRequest.of(page, size,order ,"billDate");
		Page<Telephonebill> pages = new PageImpl<Telephonebill>(TelephonebillList, pageable, TelephonebillList.size());
		return pages;
	}
	private Page<Customer> buildCustomerPagedData() {
		List<Customer> CustomerList = new ArrayList<Customer>();
		int page=0;
		int size=1;
		Sort.Direction order= Sort.Direction.ASC;
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
		CustomerList.add(Customer);
		PageRequest pageable = PageRequest.of(page, size,order ,"billDate");
		Page<Customer> pages = new PageImpl<Customer>(CustomerList, pageable, CustomerList.size());
		return pages;
	}

}
