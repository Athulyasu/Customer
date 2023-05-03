package com.customer.repository;

import com.customer.model.Customer;
import com.customer.model.Telephonebill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TelephonebillRepository extends JpaRepository<Telephonebill, Integer>
{
    @Query("SELECT C FROM Telephonebill C where C.customer.name LIKE %?1% " +
            "and billDate=?2")
    Page<Telephonebill> findBillByNameAndDate(@Param("customer_name") String customer_name, @Param("bill_date") Date bill_date, Pageable pageRequest);

    @Query("SELECT C FROM Telephonebill C ")
    Page<Telephonebill> findAllBill( Pageable pageRequest);

}
