package com.companySearch.WsClient.Response;

/**
 * Created by Kamil Best on 25.07.2017.
 */
public class Company {
    String nip;
    String fullName;
    String shortenName;
    String country;
    String state;
    String City;
    String postCode;
    String street;
    String buildingNumber;
    String localNumber;
    String phoneNumber;
    String email;
    String startDate;
    String endDate;

    String regon;

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setShortenName(String shortenName) {
        this.shortenName = shortenName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
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
