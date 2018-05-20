package com.srh_heidelberg;

import com.srh_heidelberg.model.DateCalculations;
import com.srh_heidelberg.model.Member;
import com.srh_heidelberg.model.PoolDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MemberHomePortal {

    private static Scanner scanner = new Scanner(System.in);
    private static Member memberObject = new Member();
    private static PoolDetails tempPool = new PoolDetails();
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static DatabaseConnection databaseConnection = new DatabaseConnection();


    public void welcomeMember(Member member){
        System.out.println("Hallo "+member.getMemberFirstName()+" "+member.getMemberLastName() + "\n ");
        System.out.println("Welcome to MoneyPool Portal");
        System.out.println("Please find your Details Below \n");
        System.out.println("Email : "+member.getMemberEmail());
        System.out.println("Address : "+member.getMemberAddress());
        System.out.println("IBAN: "+member.getMemberIban());
        System.out.println("Swift Code: "+member.getMemberSwiftCode());
        System.out.println("Nominee: "+member.getMemberNominee());
        System.out.println("Phone Number: "+member.getMemberPhoneNumber());
        memberObject = member;
        askForOperation();

    }

    private static void askForOperation(){
        System.out.println("Please Select An option to Proceed: ");
        System.out.println("1. Create Pool \n 2. Join Pool \n 3. Update Details");
        int option = scanner.nextInt();
        performAction(option);
    }

    private static void performAction(int option){
        switch (option){
            case 1: createPool();
                    break;
            case 2: joinPool();
                    break;
            case 3: updateDetails();
                    break;
        }
    }

    private static void updateDetails(){
    System.out.println("Selection the field to update :");
    System.out.println("1. Name \n 2. Email \n 3. Password \n 4. Address");
    System.out.println("5.IBAN \n 6. Swift Code \n 7. Nominee \n 8. Phone Number \n 9. Exit ");
    int option = scanner.nextInt();
    updateOperation(option);

    }

    private static void joinPool(){

    }

    private static  void createPool() {

        System.out.println("Enter Pool Name : ");
        tempPool.setPoolName(scanner.next());

        System.out.println("Enter Pool Duration : ");
        tempPool.setDuration(scanner.nextInt());
        tempPool.setStrength(tempPool.getDuration());

        System.out.println("Enter Individual Contribution Amount : ");
        tempPool.setIndividualShare(scanner.nextDouble());
        tempPool.setMonthlyTakeaway(tempPool.getDuration()*tempPool.getIndividualShare());

        System.out.println("Enter on which day of every month Meet up will be planned : ");
        tempPool.setMeetupDate(scanner.nextInt());

        System.out.println("Enter before which day of every month Contribution has to made : ");
        tempPool.setDepositDate(scanner.nextInt());

        System.out.println("Enter Late Payment percent charge : ");
        tempPool.setLateFeeCharge(scanner.nextFloat());

        System.out.println("Enter Start date in dd-mm-yyyy");
        String StrDate = scanner.next();

        java.util.Date javaStartdate = DateCalculations.stringToDateParse(StrDate);
        java.sql.Date sqlStartDate =  new java.sql.Date(javaStartdate.getTime());


        java.util.Date EndDate = DateCalculations.addMonth(javaStartdate,tempPool.getDuration());
        java.sql.Date sqlEndDate = new java.sql.Date(EndDate.getTime());

        tempPool.setStartDate(sqlStartDate);
        tempPool.setEndDate(sqlEndDate);
        tempPool.setPoolAdminMemberID(memberObject.getMemberID());

        loadPoolToDB(tempPool);

    }

    private static void updateOperation(int option){

        UpdateMemberDetails updateMemberDetails = new UpdateMemberDetails();

        switch (option){
            case 1: updateMemberDetails.updateName(memberObject);
                updateDetails();
                break;
            case 2: updateMemberDetails.updateEmail(memberObject);
                updateDetails();
                break;
            case 3: updateMemberDetails.updatePassword(memberObject);
                updateDetails();
                break;
            case 4:updateMemberDetails.updateAddress(memberObject);
                updateDetails();
                break;
            case 5:updateMemberDetails.updateIban(memberObject);
                updateDetails();
                break;
            case 6: updateMemberDetails.updateSwiftCode(memberObject);
                updateDetails();
                break;
            case 7:updateMemberDetails.updateNominee(memberObject);
                updateDetails();
                break;
            case 8:updateMemberDetails.updatePhoneNumber(memberObject);
                updateDetails();
                break;
            case 9: break;
            default: break;

        }
    }

    private static void loadPoolToDB(PoolDetails Pool){

        try {
            connection = databaseConnection.getDatabaseConnection(connection);
            preparedStatement = connection.prepareStatement("INSERT INTO pooldetails(PoolName,Duration,Strength," +
                    "IndividualShare,MonthlyTakeaway,MeetupDate,DepositDate,LateFeeCharge,StartDate,EndDate,PoolAdminMemberID)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1,Pool.getPoolName());
            preparedStatement.setInt(2,Pool.getDuration());
            preparedStatement.setInt(3,Pool.getStrength());
            preparedStatement.setDouble(4,Pool.getIndividualShare());
            preparedStatement.setDouble(5,Pool.getMonthlyTakeaway());
            preparedStatement.setInt(6,Pool.getMeetupDate());
            preparedStatement.setInt(7,Pool.getDepositDate());
            preparedStatement.setFloat(8,Pool.getLateFeeCharge());
            preparedStatement.setDate(9,Pool.getStartDate());
            preparedStatement.setDate(10,Pool.getEndDate());
            preparedStatement.setInt(11,Pool.getPoolAdminMemberID());

            preparedStatement.executeUpdate();
            System.out.println("Registration Successful");


        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
