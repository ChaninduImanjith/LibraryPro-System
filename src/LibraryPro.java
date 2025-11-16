import entities.*;
import repositories.*;
import services.*;
import menus.*;
import java.util.Scanner;

public class LibraryPro {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin1117";

    public static void main(String[] args) {
        login();

        Scanner scanner = new Scanner(System.in);

        BookRepository bookRepository = new BookRepository();
        MemberRepository memberRepository = new MemberRepository();
        IssuedRepository issuedRepository = new IssuedRepository();

        BookService bookService = new BookService(bookRepository, issuedRepository, scanner);
        MemberService memberService = new MemberService(memberRepository, issuedRepository, scanner);
        IssueService issueService = new IssueService(bookRepository, memberRepository, issuedRepository, scanner);
        ReportService reportService = new ReportService(memberRepository, issuedRepository, scanner);

        BookMenu bookMenu = new BookMenu(bookService, scanner);
        MemberMenu memberMenu = new MemberMenu(memberService, scanner);
        IssueMenu issueMenu = new IssueMenu(issueService, scanner);
        ReportMenu reportMenu = new ReportMenu(reportService, scanner);

        homePage(bookMenu, memberMenu, issueMenu, reportMenu, scanner);
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        while (!loggedIn) {
            clearConsole();
            System.out.println("============ LibraryPro Login ============");
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                loggedIn = true;
                System.out.println("\nLogin successful! Press Enter to continue...");
                scanner.nextLine();
            } else {
                System.out.println("\nInvalid credentials: Try again. Press Enter to continue...");
                scanner.nextLine();
            }
        }
    }

    private static void homePage(BookMenu bookMenu, MemberMenu memberMenu,
                                 IssueMenu issueMenu, ReportMenu reportMenu, Scanner scanner) {
        while (true) {
            clearConsole();
            System.out.println("============ LibraryPro - Home Page ============");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Members");
            System.out.println("3. Issue Books");
            System.out.println("4. Return Books");
            System.out.println("5. View Reports");
            System.out.println("6. Exit System");
            System.out.print("Select an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1": bookMenu.manageBooks(); break;
                case "2": memberMenu.manageMembers(); break;
                case "3": issueMenu.issueBook(); break;
                case "4": issueMenu.returnBook(); break;
                case "5": reportMenu.viewReports(); break;
                case "6":
                    System.out.println("\nExiting system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid option. Please try again. Press Enter to continue...");
                    scanner.nextLine();
            }
        }
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}