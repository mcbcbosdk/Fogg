package com.srh_heidelberg.model;

import java.util.Date;

public class PoolTransactions {

    /*
    PoolID                 int
MemberID               int
CurrentCounter         int
IndividualMonthlyShare double
WinnerFlag             int default '99'
DelayFlag              int default '0'
DelayPaymentsAmount    double default '0'
TakeawayAmount         double
PaymentDate            date
TakeawayDate           date
     */



    private int UID;
    private int PoolID;
    private int MemberID;
    private int CurrentCounter;
    private double IndividualMonthlyShare;
    private  int WinnerFlag;
    private int DelayFlag;
    private  double DelayPaymentsAmount;
    private double TakeawayAmount;
    private Date PaymentDate;
    private Date TakeawayDate;


    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getPoolID() {
        return PoolID;
    }

    public void setPoolID(int poolID) {
        this.PoolID = poolID;
    }

    public int getMemberID() {
        return MemberID;
    }

    public void setMemberID(int memberID) {
        this.MemberID = memberID;
    }

    public int getCurrentCounter() {
        return CurrentCounter;
    }

    public void setCurrentCounter(int currentCounter) {
        this.CurrentCounter = currentCounter;
    }

    public double getIndividualMonthlyShare() {
        return IndividualMonthlyShare;
    }

    public void setIndividualMonthlyShare(double individualMonthlyShare) {
        this.IndividualMonthlyShare = individualMonthlyShare;
    }

    public int getWinnerFlag() {
        return WinnerFlag;
    }

    public void setWinnerFlag(int winnerFlag) {
        this.WinnerFlag = winnerFlag;
    }

    public int getDelayFlag() {
        return DelayFlag;
    }

    public void setDelayFlag(int delayFlag) {
        this.DelayFlag = delayFlag;
    }

    public double getDelayPaymentsAmount() {
        return DelayPaymentsAmount;
    }

    public void setDelayPaymentsAmount(double delayPaymentsAmount) {
        this.DelayPaymentsAmount = delayPaymentsAmount;
    }

    public double getTakeawayAmount() {
        return TakeawayAmount;
    }

    public void setTakeawayAmount(double takeawayAmount) {
        this.TakeawayAmount = takeawayAmount;
    }

    public Date getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.PaymentDate = paymentDate;
    }

    public Date getTakeawayDate() {
        return TakeawayDate;
    }

    public void setTakeawayDate(Date takeawayDate) {
        this.TakeawayDate = takeawayDate;
    }
}
