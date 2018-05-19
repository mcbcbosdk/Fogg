package com.srh_heidelberg;

import com.srh_heidelberg.model.Member;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Scanner scanner = new Scanner(System.in);
    private static Member memberObject = new Member();
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;


    public static void main( String[] args )
    {

        askUserForOption();
    }

    private static void askUserForOption(){
        System.out.println("Please Select the Option \n");
        System.out.println("1. Register \n 2. Login \n");
        int option = scanner.nextInt();
        switch(option) {
            case 1: registerMember();
                break;
            case 2: loginMember();
                break;
            default: System.out.println("Please chose a right Option ");
                break;
        }
    }

    private static void registerMember(){
        System.out.println("Enter Member FirstName : ");
        memberObject.setMemberFirstName(scanner.next());
        System.out.println("Enter Member LastName : ");
        memberObject.setMemberLastName(scanner.next());
        System.out.println("Enter Member Email : ");
        memberObject.setMemberEmail(scanner.next());
        System.out.println("Enter Member Password : ");
        memberObject.setMemberPassword(scanner.next());
        System.out.println("Enter Member Phone number: ");
        memberObject.setMemberPhoneNumber(scanner.next());
        System.out.println("Enter Member Address: ");
        memberObject.setMemberAddress(scanner.next());
        System.out.println("Enter Member Nominee: ");
        memberObject.setMemberNominee(scanner.next());
        System.out.println("Enter Member IBAN :");
        memberObject.setMemberIban(scanner.next());
        System.out.println("Enter Member Swift Code: ");
        memberObject.setMemberSwiftCode(scanner.next());

        loadMemberToDb(memberObject);
    }

    private static void loginMember(){

        System.out.println("Enter your E-mail Id : ");
        memberObject.setMemberEmail(scanner.next());
        System.out.println("Enter your Password: ");
        memberObject.setMemberPassword(scanner.next());
        if (isValidUser(memberObject)){
            System.out.println("Valid User");
            MemberHomePortal memberHomePortal = new MemberHomePortal();
            memberHomePortal.welcomeMember(memberObject);

        }else {
            System.out.println("Invalid Credentials. Please Try Again");
            askUserForOption();
        }

    }

    private static void loadMemberToDb(Member member){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/money_pool", "panish", "panishvp");
            preparedStatement = connection.prepareStatement("INSERT INTO member(member_first_name, member_last_name, member_email,member_password, member_phone_number,member_address, member_nominee, member_iban, member_swift_code)" +
                    " VALUES (?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,member.getMemberFirstName());
            preparedStatement.setString(2, member.getMemberLastName());
            preparedStatement.setString(3,member.getMemberEmail());
            preparedStatement.setString(4,member.getMemberPassword());
            preparedStatement.setString(5,member.getMemberPhoneNumber());
            preparedStatement.setString(6,member.getMemberAddress());
            preparedStatement.setString(7,member.getMemberNominee());
            preparedStatement.setString(8,member.getMemberIban());
            preparedStatement.setString(9,member.getMemberSwiftCode());
            preparedStatement.executeUpdate();
            System.out.println("Registration Successful");
            askUserForOption();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean isValidUser(Member member){

        boolean isValid = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/money_pool", "panish", "panishvp");
            preparedStatement = connection.prepareCall("SELECT  * FROM member WHERE member_email = ? AND  member_password = ?");
            preparedStatement.setString(1,member.getMemberEmail());
            preparedStatement.setString(2,member.getMemberPassword());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.first()){
                System.out.println("New User Logged In: ");
                copyMemberDetails(resultSet);
                isValid = true;
            } else {
                System.out.println("You have entered a wrong Credentials Please Try again  ");
                isValid = false;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    private static void copyMemberDetails(ResultSet resultSet) {

        try {
            memberObject.setMemberFirstName(resultSet.getString(2));
            memberObject.setMemberLastName(resultSet.getString(3));
            memberObject.setMemberEmail(resultSet.getString(4));
            memberObject.setMemberAddress(resultSet.getString(6));
            memberObject.setMemberIban(resultSet.getString(7));
            memberObject.setMemberSwiftCode(resultSet.getString(8));
            memberObject.setMemberNominee(resultSet.getString(9));
            memberObject.setMemberPhoneNumber(resultSet.getString(10));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
