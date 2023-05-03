package com.customer.repository;

import com.customer.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface CustomerRepository extends CrudRepository<Customer, UUID>
{
    @Query("SELECT C FROM Customer C where lower(C.name) LIKE lower(concat('%', :customer_name,'%')) ")
    Page<Customer> findByName(@Param("customer_name")String customer_name, Pageable pageRequest);

    @Query("SELECT C FROM Customer C  order by C.name")
    Page<Customer> findAllCustomer(Pageable pageRequest);
}
