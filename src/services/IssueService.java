package services;

import entities.Book;
import entities.Member;
import entities.IssuedBook;
import repositories.BookRepository;
import repositories.MemberRepository;
import repositories.IssuedRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class IssueService {
    private BookRepository bookRepository;
    private MemberRepository memberRepository;
    private IssuedRepository issuedRepository;
    private Scanner scanner;

    public IssueService(BookRepository bookRepository, MemberRepository memberRepository,
                        IssuedRepository issuedRepository, Scanner scanner) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.issuedRepository = issuedRepository;
        this.scanner = scanner;
    }

    public void issueBook() {
        System.out.println("============ Issue Book ============");

        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        Member member = memberRepository.findMemberById(memberId);

        if (member == null) {
            System.out.println("\nMember not found. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        Book book = bookRepository.findBookById(bookId);

        if (book == null) {
            System.out.println("\nBook not found. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        if (book.getQuantity() <= 0) {
            System.out.println("\nBook is not available for borrowing. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        if (issuedRepository.isBookIssuedToMember(bookId, memberId)) {
            System.out.println("\nThis book is already issued to this member. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        LocalDate today = LocalDate.now();
        LocalDate dueDate = today.plusDays(14);

        IssuedBook issuedBook = new IssuedBook(bookId, memberId, today, dueDate);
        issuedRepository.addIssuedBook(issuedBook);

        book.setQuantity(book.getQuantity() - 1);

        System.out.println("\nBook issued successfully!");
        System.out.println("Due Date: " + dueDate);
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void returnBook() {
        System.out.println("============ Return Book ============");

        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        Member member = memberRepository.findMemberById(memberId);

        if (member == null) {
            System.out.println("\nMember not found. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        Book book = bookRepository.findBookById(bookId);

        if (book == null) {
            System.out.println("\nBook not found. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        IssuedBook issuedBook = issuedRepository.findIssuedRecord(bookId, memberId);
        if (issuedBook == null) {
            System.out.println("\nThis book is not issued to this member. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        LocalDate today = LocalDate.now();
        LocalDate dueDate = issuedBook.getDueDate();

        long daysOverdue = ChronoUnit.DAYS.between(dueDate, today);
        double fine = 0;

        if (daysOverdue > 0) {
            fine = daysOverdue * 50;
            System.out.printf("\nBook is overdue by %d days. Fine: %.2f LKR\n", daysOverdue, fine);
        }

        book.setQuantity(book.getQuantity() + 1);
        issuedRepository.removeIssuedBook(issuedBook);

        System.out.println("\nBook returned successfully!");
        if (fine > 0) {
            System.out.printf("Please collect the fine of %.2f LKR\n", fine);
        }
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}