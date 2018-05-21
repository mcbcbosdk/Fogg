package com.srh_heidelberg;

import com.srh_heidelberg.model.Member;

import java.util.Scanner;

public class PoolAdminPage {

    private static PoolTransactionDMLOperations dmlOperations = new PoolTransactionDMLOperations();
    private static Scanner scanner = new Scanner(System.in);

    public void AdminOperationSelect(Member PoolAdmin) {
        System.out.println("Your Pools n Members :");
        dmlOperations.getPoolMembers(PoolAdmin.getMemberID());
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



                break;
        }
    }

}
