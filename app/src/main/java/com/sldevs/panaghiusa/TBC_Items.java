package com.sldevs.panaghiusa;


public class TBC_Items {

    private String contributionid,fullname,number,date,time;

    public TBC_Items(){

    }

    public TBC_Items(String contributionid,String fullname,String number,String date,String time){
        this.contributionid = contributionid;
        this.fullname = fullname;
        this.number = number;
        this.date = date;
        this.time = time;
    }

    public String getContributionid(){
        return contributionid;
    }

    public void setContributionid(String contributionid){
        this.contributionid = contributionid;
    }

    public String getFullname(){
        return fullname;
    }

    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }

}