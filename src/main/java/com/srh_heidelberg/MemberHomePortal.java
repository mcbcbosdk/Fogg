package com.srh_heidelberg;

import com.srh_heidelberg.model.Member;

import java.util.Scanner;

public class MemberHomePortal {

    private static Scanner scanner = new Scanner(System.in);
    private static Member memberObject = new Member();

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
        System.out.println("1. Update Details \n 2. Create Pool \n 3. Join Pool");
        int option = scanner.nextInt();
        performAction(option);
    }

    private static void performAction(int option){
        switch (option){

            case 1: updateDetails();
            break;
            case 2: joinPool();
                break;
            case 3: createPool();
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

    private static  void createPool(){

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
}
