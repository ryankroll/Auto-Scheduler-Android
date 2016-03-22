package com.example.rkroll.auto_scheduler;

/**
 * Created by rkroll on 3/20/2016.
 */

import com.parse.Parse;
import com.parse.ParseUser;

public class User {
    private String username;
    private String name;
    private String password;
    private String email;
    private double payRate;
    private double phoneNumber;
    private double birthDate;
    private double hireDate;
    private boolean isManager;
    private boolean isGeneralManager;
    private boolean isFullTime;

    public User(){
        // Defualt constructor
    }

    public void setUsername(String user) {
        username = user;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public void setName(String n) {
        name = n;
    }

    public void setEmail(String e) {
        email = e;
    }

    public void setPayRate(double pay) {
        payRate = pay;
    }

    public void setPhoneNumber(double phone){
        phoneNumber = phone;
    }

    public void changeAvailability(){}

    public void requestTimeOff() {}

    public void requestShiftSwap() {}


}
