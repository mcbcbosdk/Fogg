package com.srh_heidelberg;

import com.srh_heidelberg.model.Member;
import com.srh_heidelberg.model.PoolDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PoolEnrollment {

    private static PoolDetails poolDetails = new PoolDetails();
    private static Connection connection;
    private static PreparedStatement preparedStatement = null;
    private static DatabaseConnection databaseConnection = new DatabaseConnection();
    private static Scanner scanner = new Scanner(System.in);
    private static  int memberId ;

    public void getPoolDetails(int poolId, Member member) {

        try {
            connection = databaseConnection.getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("SELECT * FROM pooldetails WHERE PoolID = ?");
            preparedStatement.setInt(1, poolId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.first()) {
                poolDetails.setPoolID(resultSet.getInt(1));
                poolDetails.setPoolAdminMemberID(resultSet.getInt(2));
                poolDetails.setPoolName(resultSet.getString(3));
                poolDetails.setDuration(resultSet.getInt(4));
                poolDetails.setStrength(resultSet.getInt(5));
                poolDetails.setIndividualShare(resultSet.getDouble(7));
                poolDetails.setMonthlyTakeaway(resultSet.getDouble(8));
                poolDetails.setStartDate(resultSet.getDate(9));
                poolDetails.setEndDate(resultSet.getDate(10));
                poolDetails.setMeetupDate(resultSet.getInt(11));
                poolDetails.setDepositDate(resultSet.getInt(12));
                memberId = member.getMemberID();
                displayPooldetails();
                askForEnrollment();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void displayPooldetails() {

        System.out.println("The pool details are Listed Below: ");
        System.out.println("Pool ID: " + poolDetails.getPoolID());
        System.out.println("Pool Name: " + poolDetails.getPoolName());
        System.out.println("Pool Duration: " + poolDetails.getDuration());
        System.out.println("Pool Strength: " + poolDetails.getStrength());
        System.out.println("Pool Individual Share: " + poolDetails.getIndividualShare());
        System.out.println("Pool Monthly Takeaway: " + poolDetails.getMonthlyTakeaway());
        System.out.println("Pool Start Date: " + poolDetails.getStartDate());
        System.out.println("Pool End Date: " + poolDetails.getEndDate());
        System.out.println("Pool MeetUp date: " + poolDetails.getMeetupDate() + "th of Every Month");
        System.out.println("Pool Deposit date: " + poolDetails.getDepositDate() + "th of Every Month");
        System.out.println("Pool LateFee Charge: " + poolDetails.getLateFeeCharge());


    }

    private static void askForEnrollment() {
        System.out.println("Please Confirm your Enrollment by Selecting an Option: ");
        System.out.println("1. Enroll Me \n 2. Don't Enroll Me 3. Exit");
        int option = scanner.nextInt();
        processOperation(option);
    }

    private static void processOperation(int option) {
        switch (option) {
            case 1:
                enrollMember();
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    private static void enrollMember() {


        try {
            preparedStatement = connection.prepareStatement("INSERT INTO pooltransactions(PoolID, MemberID, CurrentCounter,IndividualMonthlyShare, WinnerFlag, DelayFlag, DelayPaymentsAmount) " +
                    "VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setInt(1,poolDetails.getPoolID());
            preparedStatement.setInt(2, memberId);
            preparedStatement.setInt(3,-1);
            preparedStatement.setDouble(4,poolDetails.getIndividualShare());
            preparedStatement.setInt(5,-1);
            preparedStatement.setInt(6,0);
            preparedStatement.setDouble(7,0);
            preparedStatement.executeUpdate();
            System.out.println("You Are Added Successfully into the pool");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
