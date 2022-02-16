package com.sldevs.panaghiusa;

public class User {

    public String id,fullname,email,number,password,points,date,time;

    public User(){

    }

    public User(String id,String fullname,String email,String number,String password,String points,String date, String time){
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.number = number;
        this.password = password;
        this.points = points;
        this.date = date;
        this.time = time;
    }

}
