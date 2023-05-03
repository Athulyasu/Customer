package com.customer.DTO;

import java.util.Date;
import java.util.UUID;

public class TelephonebillDTO {
    private UUID customerId;
    private Date billDate;

    private Double usageInMb;
    private Integer billId;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Double getUsageInMb() {
        return usageInMb;
    }

    public void setUsageInMb(Double usageInMb) {
        this.usageInMb = usageInMb;
    }
    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }


    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
}
