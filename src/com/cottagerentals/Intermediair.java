package com.cottagerentals;

public class Intermediair {

    private String name;
    private String country;
    private double commision = 40;

    public Intermediair(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Intermediair(String name, String country, double commision) {
        this.name = name;
        this.country = country;
        this.commision = commision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCommision(double commision) {
        this.commision = commision;
    }

    public double getCommision() {
        return commision;
    }
}
