package com.customer.controller;

import com.customer.DTO.CustomerPojo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
@Controller
public class TestController {

    @GetMapping("/testApi")
    private @ResponseBody String testApi() throws Exception
    {
        List<CustomerPojo> customerPojoList = new ArrayList<CustomerPojo>();
        CustomerPojo customerPojo = new CustomerPojo();

        Date date=new Date();
        customerPojo.setName("safsadf");
        customerPojo.setPermanentAddress("DEMO ADDRESS");
        customerPojo.setCommunicationAddress("DEMO ADDRESS");
        customerPojo.setCity("KOLLAM");
        customerPojo.setDistrict("KOLLAM");
        customerPojo.setDob(date);
        customerPojo.setState("KERALA");
        customerPojo.setPhoneNo(98745632105L);
        customerPojo.setMobileNo(9874563218L);
        customerPojo.setPin(691536);
        customerPojo.setCountry("INDIA");
        customerPojoList.add(customerPojo);

        //String jsonData = new ObjectMapper().writeValueAsString(customerPojo);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject( "http://localhost:8080/customer/checkout", customerPojo , String.class );
        return response;
    }


}
