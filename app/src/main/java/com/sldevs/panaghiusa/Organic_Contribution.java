package com.sldevs.panaghiusa;

public class Organic_Contribution {

    public String contributionID,fullname,number,address,longandlat,date,time;

    public Organic_Contribution(){

    }

    public Organic_Contribution(String contributionID,String fullname,String number,String address,String longandlat,String date, String time){
        this.contributionID = contributionID;
        this.fullname = fullname;
        this.number = number;
        this.address = address;
        this.longandlat = longandlat;
        this.date = date;
        this.time = time;
    }

}