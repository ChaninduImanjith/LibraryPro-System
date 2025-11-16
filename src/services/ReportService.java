package services;

import entities.IssuedBook;
import entities.Member;
import repositories.MemberRepository;
import repositories.IssuedRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ReportService {
    private MemberRepository memberRepository;
    private IssuedRepository issuedRepository;
    private Scanner scanner;

    public ReportService(MemberRepository memberRepository, IssuedRepository issuedRepository, Scanner scanner) {
        this.memberRepository = memberRepository;
        this.issuedRepository = issuedRepository;
        this.scanner = scanner;
    }

    public void overdueBooks() {
        System.out.println("============ Overdue Books ============");

        var issuedBooks = issuedRepository.getAllIssuedBooks();
        if (issuedBooks.isEmpty()) {
            System.out.println("\nNo books currently issued.");
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            return;
        }

        LocalDate today = LocalDate.now();
        boolean foundOverdue = false;

        System.out.printf("\n%-10s %-10s %-15s %-15s %-10s\n",
                "Book ID", "Member ID", "Due Date", "Days Overdue", "Fine (LKR)");
        System.out.println("----------------------------------------------------------------");

        for (IssuedBook issuedBook : issuedBooks) {
            if (today.isAfter(issuedBook.getDueDate())) {
                long daysOverdue = ChronoUnit.DAYS.between(issuedBook.getDueDate(), today);
                double fine = daysOverdue * 50;

                System.out.printf("%-10s %-10s %-15s %-15d %-10.2f\n",
                        issuedBook.getBookId(), issuedBook.getMemberId(),
                        issuedBook.getDueDate(), daysOverdue, fine);
                foundOverdue = true;
            }
        }

        if (!foundOverdue) {
            System.out.println("\nNo overdue books found.");
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void booksIssuedPerMember() {
        System.out.println("============ Books Issued Per Member ============");

        var members = memberRepository.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("\nNo members registered in the system.");
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            return;
        }

        System.out.printf("\n%-10s %-30s %-15s\n", "Member ID", "Name", "Books Issued");
        System.out.println("--------------------------------------------------");

        boolean anyIssued = false;
        for (Member member : members) {
            int count = issuedRepository.countBooksIssuedToMember(member.getMemberId());
            if (count > 0) {
                System.out.printf("%-10s %-30s %-15d\n",
                        member.getMemberId(), member.getName(), count);
                anyIssued = true;
            }
        }

        if (!anyIssued) {
            System.out.println("\nNo books currently issued to any members.");
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}