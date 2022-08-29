package com.experis.mytunesassignment.models;

public class CustomerCountry {
    private int customerCount;
    private String country;

    public CustomerCountry(int customerCount, String country) {
        this.customerCount = customerCount;
        this.country = country;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
