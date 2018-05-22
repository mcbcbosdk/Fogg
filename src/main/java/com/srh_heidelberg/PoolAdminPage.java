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
        System.out.println("1. Add Payments \n2.Pick Winner");
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
        }
    }

}
