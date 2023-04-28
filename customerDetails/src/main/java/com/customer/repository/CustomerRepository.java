package com.customer.repository;

import com.customer.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID>
{
    @Query("SELECT C FROM Customer C where C.name LIKE %:customer_name% order by C.name")
    List<Customer> searchCustomers(@Param("customer_name") String customer_name);

}
