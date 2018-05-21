package com.srh_heidelberg;


import com.srh_heidelberg.model.PoolTransactions;

import java.sql.*;
import java.util.ArrayList;

public class PoolTransactionDMLOperations {

    private static PreparedStatement preparedStatement = null;
    private static Connection connection = null;
    private static DatabaseConnection databaseConnection = new DatabaseConnection();

    private static void insertNewBlankTransaction(int PoolID) {

    }

    private static void updateCurrentCounter() {

    }

    private static void findMaxCurrentCounter(){

    }

    public void makePaymentForMember(int PoolID,int MemeberID){


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
            preparedStatement = connection.prepareCall("select PD.PoolID,PT.MemberID from pooldetails PD JOIN pooltransactions PT ON PD.PoolID = PT.PoolID where PD.PoolAdminMemberID = ? AND PT.CurrentCounter = ? AND PT.WinnerFlag = ?");
            preparedStatement.setInt(1,AdminMemberID);
            preparedStatement.setInt(2,-1);
            preparedStatement.setInt(3,99);

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
            preparedStatement = connection.prepareCall("select 1 from pooldetails where PoolID = ? AND  PoolAdminMemberID = ?");
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.getInt(1) == 1) status = true;
            else status = false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

}
