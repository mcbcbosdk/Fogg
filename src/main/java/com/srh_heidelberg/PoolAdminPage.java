package com.srh_heidelberg;

import java.util.Collection;
import java.util.Scanner;

public class PoolAdminPage {

    private static PoolTransactionDMLOperations dmlOperations = new PoolTransactionDMLOperations();
    private static Scanner scanner = new Scanner(System.in);
    private static int PoolAdminMemberID;

    public static void AdminOperationSelect(int PoolAdmin) {
        System.out.println("Your Pools n Members :");
        dmlOperations.printPoolMembers(PoolAdmin);
        PoolAdminMemberID = PoolAdmin;
        System.out.println("Selection the field to update :");
        System.out.println("1. Add Payments \n2.Pick Winner \n3.Update Pool Details " +
                "\n4.View Winner List \n5.Show Remaining Iterations \n6.Show Pending Payments");
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
                if (dmlOperations.isValidPoolAdd(PoolID) & dmlOperations.isValidAdmin(PoolID,PoolAdminMemberID) & dmlOperations.isValidMember(PoolID,MemberID)){
                    dmlOperations.makePaymentForMember(PoolID,MemberID);
                    PoolAdminPage.AdminOperationSelect(PoolAdminMemberID);
                }
                else{
                    System.out.println("You are Not Admin for PoolID :"+PoolID +"\n OR incorrect MemberID OR Pool is Complete..Please retry.");
                    PoolAdminPage.AdminOperationSelect(PoolAdminMemberID);
                }
                break;
            case 2:
                System.out.println("Enter Pool ID :");
                PoolID = scanner.nextInt();

                if (dmlOperations.isValidAdmin(PoolID,PoolAdminMemberID) & dmlOperations.isValidPickWinner(PoolID) ) {
                    dmlOperations.pickWinnerForCurrentMonth(PoolID);
                    PoolAdminPage.AdminOperationSelect(PoolAdminMemberID);
                }
                else{
                    System.out.println("You are Not Admin for PoolID :"+PoolID +"\nOR Pool is Complete \nPlease retry.");
                    PoolAdminPage.AdminOperationSelect(PoolAdminMemberID);
                }
                break;
            case 3:
                updatePooldetails();
                break;
            case 4:
                System.out.println("Enter Pool ID :");
                PoolID = scanner.nextInt();
                dmlOperations.getPoolMembersWhoWonDisplay(PoolID);
                PoolAdminPage.AdminOperationSelect(PoolAdminMemberID);
                break;
            case 5:
                System.out.println("Enter Pool ID :");
                PoolID = scanner.nextInt();
                System.out.println("Remaining Iteration for Pool ID "+PoolID+" : "+dmlOperations.getRemainingIterations(PoolID));
                PoolAdminPage.AdminOperationSelect(PoolAdminMemberID);
                break;
            case 6:
                System.out.println("Enter Pool ID :");
                PoolID = scanner.nextInt();
                System.out.println("Members remaining to Pay for PoolID "+PoolID+" are: \n");
                dmlOperations.printPoolMembersRemainingToPay(PoolID);
                PoolAdminPage.AdminOperationSelect(PoolAdminMemberID);
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
