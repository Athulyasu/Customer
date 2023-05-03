package com.customer.model;

import javax.persistence.*;
import java.util.Date;

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

    public Double getUsageInMb() {
        return usageInMb;
    }

    public void setUsageInMb(Double usageInMb) {
        this.usageInMb = usageInMb;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
}
