package com.experis.mytunesassignment.models;

public class CustomerFull {
    private int id;
    private String firstName;
    private String lastName;
    private String company;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String phoneNo;
    private String fax;
    private String email;
    private int supportRepId;

    // region Constructors
    public CustomerFull() {}

    public CustomerFull(int id, String firstName, String lastName, String company, String address, String city, String state, String country, String postalCode, String phoneNo, String fax, String email, int supportRepId) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setCompany(company);
        setAddress(address);
        setCity(city);
        setState(state);
        setCountry(country);
        setPostalCode(postalCode);
        setPhoneNo(phoneNo);
        setFax(fax);
        setEmail(email);
        setSupportRepId(supportRepId);
    }

    // endregion Constructors

    // region getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public int getSupportRepId() {
        return supportRepId;
    }

    public void setSupportRepId(int supportRepId) {
        this.supportRepId = supportRepId;
    }
    // endregion getters and setters
}
