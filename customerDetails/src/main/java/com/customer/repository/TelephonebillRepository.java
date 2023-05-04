package com.customer.repository;

import com.customer.model.Customer;
import com.customer.model.Telephonebill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface TelephonebillRepository extends JpaRepository<Telephonebill, Integer>, CrudRepository<Telephonebill, Integer>
{
    @Query("SELECT C FROM Telephonebill C where  lower(C.customer.name) LIKE lower(concat('%', ?1,'%')) " +
            "and billDate=cast(?2 as date) ")
    Page<Telephonebill> findBillByNameAndDate(String customer_name, String bill_date, Pageable pageRequest);

    @Query("SELECT C FROM Telephonebill C ")
    Page<Telephonebill> findAllBill( Pageable pageRequest);

    @Query(value="SELECT c.name,sum(tel.usage_in_mb),Cast(c.customer_id  as varchar) customer_id FROM telephonebill tel "+
            " join customer c on c.customer_id=tel.customer_id"+
            " where tel.customer_id=:customerId "+
            "and bill_date BETWEEN cast(:fromDate as date)  AND cast(:toDate as date) group by c.name,c.customer_id",
            nativeQuery = true)
    List<Object[]> findAMonthlyBill(@Param("customerId") UUID customerId,@Param("fromDate") String fromDate,@Param("toDate") String toDate);

}
