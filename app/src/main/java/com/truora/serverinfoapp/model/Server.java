package com.truora.serverinfoapp.model;

public class Server {
    private String address;
    private String grade;
    private String country;
    private String owner;

    public Server() { }

    public Server(String address, String grade, String country, String owner) {
        this.address = address;
        this.grade = grade;
        this.country = country;
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
