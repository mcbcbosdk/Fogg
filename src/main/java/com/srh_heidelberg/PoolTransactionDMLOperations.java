package com.srh_heidelberg;


import com.srh_heidelberg.model.DateCalculations;
import com.srh_heidelberg.model.PoolTransactions;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class PoolTransactionDMLOperations {

    private static PreparedStatement preparedStatement = null;

    private static void insertNewBlankTransaction(int PoolID,int MemeberID,int counter) {

        try {
            long millis=System.currentTimeMillis();
            java.sql.Date currentDate = new java.sql.Date(millis);

            double individualContri = getIndividualContribution(PoolID);

            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("INSERT into pooltransactions " +
                    "(PoolID, MemberID, CurrentCounter, IndividualMonthlyShare, PaymentDate) " +
                    "VALUE (?,?,?,?,?)");

            preparedStatement.setInt(1,PoolID);
            preparedStatement.setInt(2,MemeberID);
            preparedStatement.setInt(3,counter);
            preparedStatement.setDouble(4,individualContri);
            preparedStatement.setDate(5,currentDate);
            preparedStatement.executeUpdate();
            System.out.println("Blank Data Entered!!!!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static double getIndividualContribution(int poolID){
        double individualContri = 0;
        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("select IndividualShare from pooldetails where PoolID = ?");
            preparedStatement.setInt(1,poolID);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            individualContri = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return individualContri;
    }

    private static void updateCurrentCounter(int poolID) {
        int oldCounter = getCounterFromPoolDetails(poolID);
        int newCounter ;

        if (oldCounter == -1) {
            newCounter = 1;
        }
        else{
            newCounter = ++oldCounter;
        }

        setCounterInPooldetails(poolID,newCounter);
    }

    private static void setCounterInPooldetails(int poolID,int counter){

        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("UPDATE pooldetails set CurrentCounter = ? where PoolID = ?");
            preparedStatement.setInt(1,counter);
            preparedStatement.setInt(2,poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void setCounterInPoolTransaction (int poolID, int counter){

        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("UPDATE pooltransactions set CurrentCounter = ? " +
                    "where PoolID = ? and CurrentCounter = ?");
            preparedStatement.setInt(1,counter);
            preparedStatement.setInt(2,poolID);
            preparedStatement.setInt(3,0);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static int getCounterFromPoolTransaction(int poolID){
        int countFetched=0;
        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("SELECT MAX(CurrentCounter) from pooltransactions " +
                    "where PoolID = ? ");
            preparedStatement.setInt(1,poolID);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            countFetched = rs.getInt(1);
            return countFetched;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static int getCounterFromPoolDetails (int poolID) {
        int countFetched = 0;
        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("select CurrentCounter from pooldetails where PoolID = ?");
            preparedStatement.setInt(1,poolID);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            countFetched = rs.getInt(1);
            return countFetched;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countFetched;
    }

    private static int getStrenghtOfPool (int poolID) {
        int strength = 0;
        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("select Strength from pooldetails where PoolID = ?");
            preparedStatement.setInt(1, poolID);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            strength = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strength;
    }

    private static int groupPaymentDoneCount (int poolID,int counter){
        // will give count of people made payment for that Months cycle
        int payerCount = 0;
        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("select count(MemberID) from pooltransactions " +
                    "where PoolID = ? and CurrentCounter = ?");
            preparedStatement.setInt(1,poolID);
            preparedStatement.setInt(2,counter);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            payerCount = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payerCount;
    }

    private static  int getDepositDay(int poolID){
        int depositDay = 0;
        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("SELECT DepositDate from pooldetails " +
                    "where PoolID = ?");
            preparedStatement.setInt(1,poolID);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            depositDay = rs.getInt(1);
            return depositDay;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return depositDay;
    }

    private static java.util.Date getStartDate(int poolID){
        java.util.Date startDate = new java.util.Date();
        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("SELECT StartDate from pooldetails " +
                    "where PoolID = ?");
            preparedStatement.setInt(1,poolID);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            startDate = rs.getDate(1);
            return startDate;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    private static boolean isDelay (int poolID){
        int depositDay = getDepositDay(poolID);
        int currentCounter = getCounterFromPoolDetails(poolID);
        Date startDate = (Date) getStartDate(poolID);

        java.util.Date monthStartDate =  DateCalculations.addMonth(startDate,currentCounter);

        java.util.Date depositDate = DateCalculations.addDay(monthStartDate,depositDay);

        java.util.Date currentDate = new java.util.Date();

        if (currentDate.after(depositDate))
            return true;
        else
            return false;

    }

    public void makePaymentForMember(int PoolID,int MemeberID){


        int strenght = getStrenghtOfPool(PoolID);
        int currentMaxCounter = getCounterFromPoolDetails(PoolID);
        int payersCount = groupPaymentDoneCount(PoolID,currentMaxCounter);

        System.out.println("Strenght : " + strenght);
        System.out.println("Current Counter : " + currentMaxCounter);
        System.out.println("Payer Count :" +payersCount);

        if (isDelay(PoolID))
            updateDelayPayments(PoolID,MemeberID,currentMaxCounter);
        else{
            if (currentMaxCounter <= strenght){
                if ( (payersCount == strenght) ){
                    updateCurrentCounter(PoolID);
                    currentMaxCounter = getCounterFromPoolDetails(PoolID);
                }
                insertNewBlankTransaction(PoolID,MemeberID,currentMaxCounter);
                System.out.println("Transaction added");
            }
        }
    }

    private static double getLateFeeCharge(int poolID){
        double lateFeeCharge = 0;
        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("select LateFeeCharge from pooldetails where PoolID = ?");
            preparedStatement.setInt(1,poolID);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            lateFeeCharge =  rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lateFeeCharge;
    }

    private static int getMonthlyTakeaway(int poolID){
        int monthlyTakeway = 0;
        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("select MonthlyTakeaway from pooldetails where PoolID = ?");
            preparedStatement.setInt(1,poolID);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            monthlyTakeway =  rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyTakeway;
    }

    private void updateDelayPayments(int poolID,int memberID,int currentCounter) {
        java.util.Date tempDate = new java.util.Date();
        Date currentDate = new Date(tempDate.getTime());

        double individualContribution = getIndividualContribution(poolID);
        double lateFeeCharge = getLateFeeCharge(poolID);

        double latePaymentAmount = (lateFeeCharge/100)*individualContribution;

        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("UPDATE pooltransactions set " +
                    "DelayFlag = ? , DelayPaymentsAmount = ? ,PaymentDate = ?" +
                    "where PoolID = ? and MemberID = ? and  CurrentCounter = ? and PaymentDate is null ");
            preparedStatement.setInt(1,1);
            preparedStatement.setDouble(2,latePaymentAmount);
            preparedStatement.setDate(3,currentDate);
            preparedStatement.setInt(4,poolID);
            preparedStatement.setInt(5,memberID);
            preparedStatement.setInt(6,currentCounter);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void addWinner(int poolID,int counter){
        ArrayList<Integer> poolMembersRemainingToWin = (ArrayList<Integer>) getPoolMemberRemainingToWin(poolID);

        Random rand = new Random();
        int randomElement = poolMembersRemainingToWin.get(rand.nextInt(poolMembersRemainingToWin.size()));

        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("UPDATE pooltransactions set WinnerFlag = 1 where PoolID = ? and CurrentCounter = ? and MemberID = ?");
            preparedStatement.setInt(1,poolID);
            preparedStatement.setInt(2,counter);
            preparedStatement.setInt(3,randomElement);
            preparedStatement.executeUpdate();

            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("UPDATE pooltransactions set WinnerFlag = 0 where PoolID = ? and CurrentCounter = ? and WinnerFlag != ?");
            preparedStatement.setInt(1,poolID);
            preparedStatement.setInt(2,counter);
            preparedStatement.setInt(3,1);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void TakeawayAmounts(){

    }

    private static Collection<Integer> getPoolMembersRemainingToPay(int poolID){
        Collection<Integer> allPoolMembers = getPoolMembers(poolID);
        Collection<Integer> poolMembersWhoPaid = getPoolMembersWhoPaid(poolID);

        allPoolMembers.removeAll(poolMembersWhoPaid);

        return allPoolMembers;
    }


    public  void printPoolMembers(int AdminMemberID){
        try {

            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("select PD.PoolID,PT.MemberID from pooldetails PD JOIN pooltransactions PT " +
                    "ON PD.PoolID = PT.PoolID where PD.PoolAdminMemberID = ? AND PT.CurrentCounter = ? AND PT.WinnerFlag = ?");
            preparedStatement.setInt(1,AdminMemberID);
            preparedStatement.setInt(2,-1);
            preparedStatement.setInt(3,99);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("Member List of your Pools :\n");
            System.out.println("Pool ID | Member ID");
            while (rs.next()){
                System.out.println(rs.getInt(1 )+"       | "+rs.getInt(2 )+"    ");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean isValidAdmin(int PoolID,int AdminID){
        boolean status = false;
        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("select count(*) from pooldetails where PoolID = ? AND  PoolAdminMemberID = ?");
            preparedStatement.setInt(1,PoolID);
            preparedStatement.setInt(2,AdminID);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            System.out.println(rs.getInt(1));

            if (rs.getInt(1) == 1) status = true;
            else status = false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean isValidMember(int PoolID,int MemberID){
        boolean status = false;
        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("select count(*) from pooltransactions " +
                    "where PoolID = ? AND  MemberID = ? AND CurrentCounter = ? and WinnerFlag = ? ");
            preparedStatement.setInt(1,PoolID);
            preparedStatement.setInt(2,MemberID);
            preparedStatement.setInt(3,-1);
            preparedStatement.setInt(4,99);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            System.out.println(rs.getInt(1));

            if (rs.getInt(1) == 1) status = true;
            else status = false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    private static Collection<Integer> getPoolMembers(int poolID){

        Collection<Integer> allPoolMembers = new ArrayList<>();

        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("select MemberID from pooltransactions where PoolID = ? AND CurrentCounter = ?");
            preparedStatement.setInt(1,poolID);
            preparedStatement.setInt(2,-1);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                allPoolMembers.add(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPoolMembers;
    }

    private static Collection<Integer> getPoolMembersWhoPaid(int poolID){

        Collection<Integer> poolMembersWhoPaid = new ArrayList<>();

        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("select MemberID from pooltransactions where PoolID = ? AND " +
                    "CurrentCounter = (select MAX(CurrentCounter) from pooltransactions where PoolID = ?)");
            preparedStatement.setInt(1,poolID);
            preparedStatement.setInt(2,poolID);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                poolMembersWhoPaid.add(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return poolMembersWhoPaid;
    }

    private static void fillUpBlankTransaction(int PoolID,int counter) {

        try {
            long millis=System.currentTimeMillis();
            java.sql.Date currentDate = new java.sql.Date(millis);

            double individualContri = getIndividualContribution(PoolID);
            ArrayList<Integer> membersRemainingToPay= (ArrayList<Integer>) getPoolMembersRemainingToPay(PoolID);

            for (int i = 0; i < membersRemainingToPay.size(); i++) {
                preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("INSERT into pooltransactions " +
                        "(PoolID, MemberID, CurrentCounter, IndividualMonthlyShare) " +
                        "VALUE (?,?,?,?)");

                preparedStatement.setInt(1,PoolID);
                preparedStatement.setInt(2,membersRemainingToPay.get(i));
                preparedStatement.setInt(3,counter);
                preparedStatement.setDouble(4,individualContri);
                preparedStatement.executeUpdate();
                System.out.println("Blank Data Entered!!!!");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static Collection<Integer> getPoolMemberRemainingToWin(int poolID){
        Collection<Integer> allPoolMembers = getPoolMembers(poolID);
        Collection<Integer> poolMembersWhoWon = getPoolMembersWhoWon(poolID);

        allPoolMembers.removeAll(poolMembersWhoWon);

        return allPoolMembers;
    }

    private static Collection<Integer> getPoolMembersWhoWon(int poolID){
        Collection<Integer> poolMembersWhoWon = new ArrayList<>();

        try {
            preparedStatement = DatabaseConnection.singletonConnectionToDb.prepareCall("select MemberID from pooltransactions WHERE PoolID = ? AND WinnerFlag = ?");
            preparedStatement.setInt(1,poolID);
            preparedStatement.setInt(2,1);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                poolMembersWhoWon.add(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return poolMembersWhoWon;
    }

    public void pickWinnerForCurrentMonth (int poolID){
        int strenght = getStrenghtOfPool(poolID);
        int currentCounter = getCounterFromPoolDetails(poolID);
        int payerCount = groupPaymentDoneCount(poolID,currentCounter);

        if (payerCount < strenght){
            fillUpBlankTransaction(poolID,currentCounter);
            addWinner(poolID,currentCounter);
        }
        else if (payerCount == strenght){
            addWinner(poolID,currentCounter);
        }
    }
}
