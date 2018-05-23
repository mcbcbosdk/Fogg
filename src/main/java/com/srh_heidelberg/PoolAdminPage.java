package com.srh_heidelberg;

import com.srh_heidelberg.model.Member;

import java.util.Scanner;

public class PoolAdminPage {

    private static PoolTransactionDMLOperations dmlOperations = new PoolTransactionDMLOperations();
    private static Scanner scanner = new Scanner(System.in);
    private static int PoolAdminMemberID;

    public static void AdminOperationSelect(int PoolAdmin) {
        System.out.println("Your Pools n Members :");
        dmlOperations.getPoolMembers(PoolAdmin);
        PoolAdminMemberID = PoolAdmin;
        System.out.println("Selection the field to update :");
        System.out.println("1. Add Payments \n2.Pick Winner \n3.Update Pool Details");
        int option = scanner.nextInt();
        performSelection(option);

    }

    private static void performSelection(int option) {
        switch (option) {
            case 1:
                System.out.println("Enter Pool ID :");
                int PoolID = scanner.nextInt();
                System.out.println("Enter Member ID :");
                int MemberID = scanner.nextInt();
                if (dmlOperations.isValidAdmin(PoolID,PoolAdminMemberID) & dmlOperations.isValidMember(PoolID,MemberID))
                    dmlOperations.makePaymentForMember(PoolID,MemberID);
                else{
                    System.out.println("You are Not Admin for PoolID :"+PoolID +"\nPlease retry.");
                    PoolAdminPage.AdminOperationSelect(PoolID);
                }
                break;
            case 2: break;
            case 3:
                updatePooldetails();
                break;


        }

    }


    private static void updatePooldetails() {
        System.out.println("Selection the field to update :");
        System.out.println("1. Pool Name  \n 2. Enter Duration \n 3. Enter Strength \n 4. Enter Current Counter");
        System.out.println("5. Enter Individual share \n 6. Enter Monthly Takeaway \n 7. Enter Start date \n 8. Enter End date \n 9. Enter Meet up Date ");
        System.out.println("10.Enter Deposit Date \n 11. Enter Late fee charge " ) ;
        int option = scanner.nextInt();
        updateOperation(option);
    }


    private static void updateOperation(int option) {
        UpdatePoolDetails updatePoolDetails = new UpdatePoolDetails();

        switch (option) {
            case 1:
                updatePoolDetails.updatePoolName();
                updatePooldetails();
                break;
            case 2:
                updatePoolDetails.updateDuration();
                updatePooldetails();
                break;
            case 3:
                updatePoolDetails.updateStrength();
                updatePooldetails();
                break;
            case 4:
                updatePoolDetails.updateCurrentCounter();
                updatePooldetails();
                break;
            case 5:
                updatePoolDetails.updateIndividualShare();
                updatePooldetails();
                break;
            case 6:
                updatePoolDetails.updateMonthlyTakeaway();
                updatePooldetails();
                break;
            case 7:
                updatePoolDetails.updateStartDate();
                updatePooldetails();
                break;
            case 8:
                updatePoolDetails.updateEndDate();
                updatePooldetails();
                break;
            case 9:
                updatePoolDetails.updateMeetupDate();
                updatePooldetails();
                break;
            case 10:
                updatePoolDetails.updateDepositDate();
                updatePooldetails();
                break;
            case 11:
                updatePoolDetails.updateLateFeeCharge();
                updatePooldetails();
                break;
            case 12:
                break;
            default:
                break;
        }
    }

}
