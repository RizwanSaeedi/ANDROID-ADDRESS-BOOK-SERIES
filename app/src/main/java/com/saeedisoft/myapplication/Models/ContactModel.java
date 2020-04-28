package com.saeedisoft.myapplication.Models;

public class ContactModel {
    // data memebers
    String name;
    String mob;
    String address;

    // default constructor
    public ContactModel() {
        this.name = "";
        this.mob = "";
        this.address = "";
    }

    // param constructor
    public ContactModel(String name, String mob, String address) {
        this.name = name;
        this.mob = mob;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
