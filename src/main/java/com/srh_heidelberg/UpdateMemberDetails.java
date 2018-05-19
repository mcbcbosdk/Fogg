package com.srh_heidelberg;

import com.srh_heidelberg.model.Member;

import java.sql.*;
import java.util.Scanner;

public class UpdateMemberDetails {

    private static Scanner scanner = new Scanner(System.in);
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;

    public void updateName(Member member){
        getConnection();
        System.out.println("Enter your First Name to Update: ");
        String firstName = scanner.next();
        System.out.println("Enter your Last Name to Update: ");
        String lastName = scanner.next();
        try {
            preparedStatement = connection.prepareStatement("UPDATE  member  SET  member_first_name = ?,  member_last_name = ? WHERE member_email= ?");
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,member.getMemberEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmail(Member member){
        getConnection();
        System.out.println("Enter the New Email to Update: ");
        String email = scanner.next();
        try {
            preparedStatement = connection.prepareStatement("UPDATE  member  SET  member_email = ? WHERE member_email= ?");
            preparedStatement.setString(1,email);
            preparedStatement.setString(2, member.getMemberEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updatePassword(Member member){
        getConnection();
        System.out.println("Enter your Old Password: ");
        String oldPassword = scanner.next();
        System.out.println("Enter your New Password: ");
        String newPassword = scanner.next();
        if (isOldPasswordTrue(oldPassword,member.getMemberEmail())){
            try{
                preparedStatement = connection.prepareStatement("UPDATE  member  SET  member_password = ? WHERE member_email= ?");
                preparedStatement.setString(1,newPassword);
                preparedStatement.setString(2,member.getMemberEmail());
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void updateAddress(Member member){
        getConnection();
        System.out.println("Enter the New Address to Update: ");
        String address = scanner.next();
        try {
            preparedStatement = connection.prepareStatement("UPDATE  member  SET  member_address = ? WHERE member_email= ?");
            preparedStatement.setString(1,address);
            preparedStatement.setString(2, member.getMemberEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateIban(Member member){
        getConnection();
        System.out.println("Enter the New IBAN to Update: ");
        String iban = scanner.next();
        try {
            preparedStatement = connection.prepareStatement("UPDATE  member  SET  member_iban = ? WHERE member_email= ?");
            preparedStatement.setString(1,iban);
            preparedStatement.setString(2, member.getMemberEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateSwiftCode(Member member){
        getConnection();
        System.out.println("Enter the New Swift Code to Update: ");
        String swiftCode = scanner.next();
        try {
            preparedStatement = connection.prepareStatement("UPDATE  member  SET  member_swift_code = ? WHERE member_email= ?");
            preparedStatement.setString(1,swiftCode);
            preparedStatement.setString(2, member.getMemberEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateNominee(Member member){
        getConnection();
        System.out.println("Enter the New Nominee to Update: ");
        String nominee = scanner.next();
        try {
            preparedStatement = connection.prepareStatement("UPDATE  member  SET  member_nominee = ? WHERE member_email= ?");
            preparedStatement.setString(1,nominee);
            preparedStatement.setString(2, member.getMemberEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePhoneNumber(Member member){
        getConnection();
        System.out.println("Enter the New Email to Update: ");
        String phoneNumber = scanner.next();
        try {
            preparedStatement = connection.prepareStatement("UPDATE  member  SET  member_phone_number = ? WHERE member_email= ?");
            preparedStatement.setString(1,phoneNumber);
            preparedStatement.setString(2, member.getMemberEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/money_pool", "panish", "panishvp");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static boolean isOldPasswordTrue(String oldPassword, String email){
        getConnection();
        boolean isValid = false;
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM  member WHERE  member_email = ? AND  member_password = ?");
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,oldPassword);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()){
             isValid = true;
            }else{
                isValid = false;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return isValid;
    }
}
