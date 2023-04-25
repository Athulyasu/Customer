package com.customer.pojo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotNull;

import java.util.Date;

public class CustomerPojo
{
    @NotNull
    @Pattern(regexp = "^[A-Za-z\\s,\\.]*$")
    private String name;
    @Pattern(regexp = "^[A-Za-z\\s,\\.]*$")
    private String permanentAddress;
    @Pattern(regexp = "^[A-Za-z\\s,\\.]*$")
    private String communicationAddress;
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String city;
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String district;
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String state;
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String country;
    private long phoneNo;
    //@Range(min=6,max=6)
    private int pin;
    private Date dob;
    @NotNull
    private long mobileNo;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getCommunicationAddress() {
        return communicationAddress;
    }

    public void setCommunicationAddress(String communicationAddress) {
        this.communicationAddress = communicationAddress;
    }    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(long phoneNo) {
        this.phoneNo = phoneNo;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
}
