package menus;

import services.ReportService;
import java.util.Scanner;

public class ReportMenu {
    private ReportService reportService;
    private Scanner scanner;

    public ReportMenu(ReportService reportService, Scanner scanner) {
        this.reportService = reportService;
        this.scanner = scanner;
    }

    public void viewReports() {
        while (true) {
            clearConsole();
            System.out.println("============ View Reports ============");
            System.out.println("1. Overdue Books");
            System.out.println("2. Books Issued Per Member");
            System.out.println("3. Back to Home");
            System.out.print("Select an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1": reportService.overdueBooks(); break;
                case "2": reportService.booksIssuedPerMember(); break;
                case "3": return;
                default:
                    System.out.println("\nInvalid option. Please try again. Press Enter to continue...");
                    scanner.nextLine();
            }
        }
    }

    private void clearConsole() {
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