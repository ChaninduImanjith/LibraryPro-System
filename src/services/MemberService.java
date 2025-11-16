package services;

import entities.Member;
import repositories.MemberRepository;
import repositories.IssuedRepository;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MemberService {
    private MemberRepository memberRepository;
    private IssuedRepository issuedRepository;
    private Scanner scanner;

    public MemberService(MemberRepository memberRepository, IssuedRepository issuedRepository, Scanner scanner) {
        this.memberRepository = memberRepository;
        this.issuedRepository = issuedRepository;
        this.scanner = scanner;
    }

    public void addMember() {
        System.out.println("============ Add New Member ============");

        String memberId;
        while (true) {
            System.out.print("Enter Member ID: ");
            memberId = scanner.nextLine();

            if (memberRepository.isMemberIdExists(memberId)) {
                System.out.println("Member ID already exists. Please enter a different ID.");
            } else {
                break;
            }
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        String contact;
        while (true) {
            System.out.print("Enter Contact Number: ");
            contact = scanner.nextLine();
            if (isValidPhoneNumber(contact)) {
                break;
            }
            System.out.println("Invalid phone number. Please enter a valid phone number.");
        }

        String email;
        while (true) {
            System.out.print("Enter Email: ");
            email = scanner.nextLine();
            if (isValidEmail(email)) {
                break;
            }
            System.out.println("Invalid email format. Please enter a valid email.");
        }

        Member member = new Member(memberId, name, contact, email);
        memberRepository.addMember(member);

        System.out.println("\nMember added successfully! Press Enter to continue...");
        scanner.nextLine();
    }

    public void updateMember() {
        System.out.println("============ Update Member ============");

        System.out.print("Enter Member ID to update: ");
        String memberId = scanner.nextLine();
        Member member = memberRepository.findMemberById(memberId);

        if (member == null) {
            System.out.println("\nMember not found. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        System.out.println("\nCurrent Member Details:");
        System.out.println("Member ID: " + member.getMemberId());
        System.out.println("Name: " + member.getName());
        System.out.println("Contact: " + member.getContact());
        System.out.println("Email: " + member.getEmail());

        System.out.println("\nEnter new details:");

        System.out.print("Name [" + member.getName() + "]: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            member.setName(name);
        }

        while (true) {
            System.out.print("Contact [" + member.getContact() + "]: ");
            String contact = scanner.nextLine();
            if (contact.isEmpty()) {
                break;
            }
            if (isValidPhoneNumber(contact)) {
                member.setContact(contact);
                break;
            }
            System.out.println("Invalid phone number. Please enter a valid phone number.");
        }

        while (true) {
            System.out.print("Email [" + member.getEmail() + "]: ");
            String email = scanner.nextLine();
            if (email.isEmpty()) {
                break;
            }
            if (isValidEmail(email)) {
                member.setEmail(email);
                break;
            }
            System.out.println("Invalid email format. Please enter a valid email.");
        }

        System.out.println("\nMember updated successfully! Press Enter to continue...");
        scanner.nextLine();
    }

    public void deleteMember() {
        System.out.println("============ Delete Member ============");

        System.out.print("Enter Member ID to delete: ");
        String memberId = scanner.nextLine();
        Member member = memberRepository.findMemberById(memberId);

        if (member == null) {
            System.out.println("\nMember not found. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        if (issuedRepository.hasMemberIssuedBooks(memberId)) {
            System.out.println("\nCannot delete member. They have books issued. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        memberRepository.deleteMember(member);
        System.out.println("\nMember deleted successfully! Press Enter to continue...");
        scanner.nextLine();
    }

    public void searchMember() {
        System.out.println("===== Search Member =====");

        System.out.print("Enter Member ID to search: ");
        String memberId = scanner.nextLine();
        Member member = memberRepository.findMemberById(memberId);

        if (member == null) {
            System.out.println("\nMember not found. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        System.out.println("\nMember Details:");
        System.out.println("Member ID: " + member.getMemberId());
        System.out.println("Name: " + member.getName());
        System.out.println("Contact: " + member.getContact());
        System.out.println("Email: " + member.getEmail());

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void viewAllMembers() {
        System.out.println("============ All Members ============");

        var members = memberRepository.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("\nNo members registered in the system.");
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            return;
        }

        System.out.printf("\n%-10s %-30s %-15s %-30s\n",
                "Member ID", "Name", "Contact", "Email");
        System.out.println("-------------------------------------------------------------------------------");

        for (Member member : members) {
            System.out.println(member);
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private boolean isValidPhoneNumber(String phone) {
        return Pattern.matches("\\d{10}", phone);
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
    }
}