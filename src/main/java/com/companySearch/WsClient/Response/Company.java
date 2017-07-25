package com.companySearch.WsClient.Response;

/**
 * Company class entity , contains specified values.
 * Created by Kamil Best on 25.07.2017.
 */
public class Company {
    private String nip;
    private String fullName;
    private String shortenName;
    private String country;
    private String state;
    private String City;
    private String postCode;
    private String street;
    private String buildingNumber;
    private String localNumber;
    private String phoneNumber;
    private String email;
    private String startDate;
    private String endDate;

    private String regon;

    String getRegon() {
        return regon;
    }

    void setRegon(String regon) {
        this.regon = regon;
    }

    void setNip(String nip) {
        this.nip = nip;
    }

    void setFullName(String fullName) {
        this.fullName = fullName;
    }

    void setShortenName(String shortenName) {
        this.shortenName = shortenName;
    }

    void setCountry(String country) {
        this.country = country;
    }

    void setState(String state) {
        this.state = state;
    }

    void setCity(String city) {
        City = city;
    }

    void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    void setStreet(String street) {
        this.street = street;
    }

    void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    void setEmail(String email) {
        this.email = email;
    }

    void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "nip: " + nip + '\n' +
                "fullName: " + fullName + '\n' +
                "shortenName: " + shortenName + '\n' +
                "country: " + country + '\n' +
                "state: " + state + '\n' +
                "City: " + City + '\n' +
                "postCode: " + postCode + '\n' +
                "street: " + street + '\n' +
                "buildingNumber: " + buildingNumber + '\n' +
                "localNumber: " + localNumber + '\n' +
                "phoneNumber: " + phoneNumber + '\n' +
                "email: " + email + '\n' +
                "startDate: " + startDate + '\n' +
                "endDate: " + endDate;
    }
}
