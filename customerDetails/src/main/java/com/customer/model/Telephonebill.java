package com.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="telephonebill")
public class Telephonebill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bill_generator")
    @SequenceGenerator(name="bill_generator", sequenceName = "telephonebill_bill_id_seq", allocationSize = 1)
    @Column(name = "bill_id")
    private Integer billId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
    private Date billDate;
    private Double usageInMb;

}
