package com.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"mobileNo"})
)
public class Customer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "customer_id", updatable = false, nullable = false)
    private UUID customerId;
    private String name;
    private String permanentAddress;
    private String communicationAddress;

    private String city;
    private String district;
    private String state;
    private String country;

    private long phoneNo;
    private long mobileNo;
    private int pin;
    private Date dob;

}
