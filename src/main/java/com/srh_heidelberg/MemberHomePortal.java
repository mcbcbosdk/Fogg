package com.srh_heidelberg;

import com.srh_heidelberg.model.Member;

import java.util.Scanner;

public class MemberHomePortal {

    private static Scanner scanner = new Scanner(System.in);

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
    }

    private static void askForOperation(){
        System.out.println("Please Select An option to Proceed: ");
        System.out.println("1. Update Details \n 2. Create Pool \n 3. Join Pool");
        int option = scanner.nextInt();
        performAction(option);
    }

    private static void performAction(int option){
        switch (option){
            case 1: break;
            case 2: break;
            case 3: break;
        }
    }

    private static void updateDetails(){

    }

    private static void joinPool(){

    }

    private static  void createPool(){

    }
}
