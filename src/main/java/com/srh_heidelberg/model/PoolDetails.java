package com.srh_heidelberg.model;


/*
*   PoolID            int              not null
    primary key,
  PoolAdminMemberID int              not null,
  PoolName          varchar(45)      not null,
  Duration          int              not null,
  Strength          int              not null,
  CurrentCounter    int default '-1' not null,
  IndividualShare   double           null,
  MonthlyTakeaway   double           null,
  StartDate         datetime         not null,
  EndDate           datetime         null,
  MeetupDate        int              null,
  DepositDate       int              null,
  LateFeeCharge     decimal          null
*
*
* */


import java.util.Date;

public class PoolDetails {

private int PoolID;
private int PoolAdminMemberID;
private String PoolName;
private int Duration;
private int Strength;
private int CurrentCounter;
private double IndividualShare;
private double MonthlyTakeaway;
private Date StartDate = new Date();
private Date EndDate = new Date();
private int MeetupDate;
private int DepositDate;
private float LateFeeCharge;


    public int getPoolID() {
        return PoolID;
    }

    public void setPoolID(int poolID) {
        this.PoolID = poolID;
    }

    public int getPoolAdminMemberID() {
        return PoolAdminMemberID;
    }

    public void setPoolAdminMemberID(int poolAdminMemberID) {
        this.PoolAdminMemberID = poolAdminMemberID;
    }

    public String getPoolName() {
        return PoolName;
    }

    public void setPoolName(String poolName) {
        this.PoolName = poolName;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        this.Duration = duration;
    }

    public int getStrength() {
        return Strength;
    }

    public void setStrength(int strength) {
        this.Strength = strength;
    }

    public int getCurrentCounter() {
        return CurrentCounter;
    }

    public void setCurrentCounter(int currentCounter) {
        this.CurrentCounter = currentCounter;
    }

    public double getIndividualShare() {
        return IndividualShare;
    }

    public void setIndividualShare(double individualShare) {
        this.IndividualShare = individualShare;
    }

    public double getMonthlyTakeaway() {
        return MonthlyTakeaway;
    }

    public void setMonthlyTakeaway(double monthlyTakeaway) {
        this.MonthlyTakeaway = monthlyTakeaway;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        this.StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        this.EndDate = endDate;
    }

    public int getMeetupDate() {
        return MeetupDate;
    }

    public void setMeetupDate(int meetupDate) {
        this.MeetupDate = meetupDate;
    }

    public int getDepositDate() {
        return DepositDate;
    }

    public void setDepositDate(int depositDate) {
        this.DepositDate = depositDate;
    }

    public float getLateFeeCharge() {
        return LateFeeCharge;
    }

    public void setLateFeeCharge(float lateFeeCharge) {
        this.LateFeeCharge = lateFeeCharge;
    }
}
