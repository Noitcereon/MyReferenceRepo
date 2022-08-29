package com.experis.mytunesassignment.models;

public class CustomerSpender {
    private String firstName;
    private String lastName;
    private double total;

    public CustomerSpender(String firstName, String lastName, double total) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.total = total;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
