package com.srh_heidelberg;


import com.srh_heidelberg.model.PoolTransactions;

import java.sql.*;
import java.util.ArrayList;

public class PoolTransactionDMLOperations {

    private static PreparedStatement preparedStatement = null;
    private static Connection connection = null;
    private static DatabaseConnection databaseConnection = new DatabaseConnection();

    private static void insertNewBlankTransaction(int PoolID,int MemeberID,int counter) {

        try {
            long millis=System.currentTimeMillis();
            java.sql.Date currentDate = new java.sql.Date(millis);

            double individualContri = getIndividualContribution(PoolID);

            preparedStatement = connection.prepareCall("INSERT into pooltransactions " +
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
            preparedStatement = connection.prepareCall("select IndividualShare from pooldetails where PoolID = ?");
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
            preparedStatement = connection.prepareCall("UPDATE pooldetails set CurrentCounter = ? where PoolID = ?");
            preparedStatement.setInt(1,counter);
            preparedStatement.setInt(2,poolID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void setCounterInPoolTransaction (int poolID, int counter){

        try {
            preparedStatement = connection.prepareCall("UPDATE pooltransactions set CurrentCounter = ? " +
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
            preparedStatement = connection.prepareCall("SELECT MAX(CurrentCounter) from pooltransactions " +
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
            preparedStatement = connection.prepareCall("select CurrentCounter from pooldetails where PoolID = ?");
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
            preparedStatement = connection.prepareCall("select Strength from pooldetails where PoolID = ?");
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
            preparedStatement = connection.prepareCall("select count(MemberID) from pooltransactions " +
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

    public void makePaymentForMember(int PoolID,int MemeberID){
        connection = databaseConnection.getDatabaseConnection(connection);

        int strenght = getStrenghtOfPool(PoolID);
        int currentMaxCounter = getCounterFromPoolDetails(PoolID);
        int payersCount = groupPaymentDoneCount(PoolID,currentMaxCounter);

        System.out.println("Strenght : " + strenght);
        System.out.println("Current Counter : " + currentMaxCounter);
        System.out.println("Payer Count :" +payersCount);

        if (currentMaxCounter <= strenght){
            if ( (payersCount == strenght) ){
                updateCurrentCounter(PoolID);
                currentMaxCounter = getCounterFromPoolDetails(PoolID);
            }
            insertNewBlankTransaction(PoolID,MemeberID,currentMaxCounter);
            System.out.println("Transaction added");
        }



    }
    private static void addWinner(){

    }

    private static void TakeawayAmounts(){

    }

    private static void getWinnerList(){

    }
    private static void getRemainingWinnerList(){

    }
    public  void getPoolMembers (int AdminMemberID){
        try {

            connection = databaseConnection.getDatabaseConnection(connection);
            preparedStatement = connection.prepareCall("select PD.PoolID,PT.MemberID from pooldetails PD JOIN pooltransactions PT " +
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
            connection = databaseConnection.getDatabaseConnection(connection);
            preparedStatement = connection.prepareCall("select count(*) from pooldetails where PoolID = ? AND  PoolAdminMemberID = ?");
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
            connection = databaseConnection.getDatabaseConnection(connection);
            preparedStatement = connection.prepareCall("select count(*) from pooltransactions " +
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

}
