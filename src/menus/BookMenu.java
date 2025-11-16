package menus;

import services.BookService;
import java.util.Scanner;

public class BookMenu {
    private BookService bookService;
    private Scanner scanner;

    public BookMenu(BookService bookService, Scanner scanner) {
        this.bookService = bookService;
        this.scanner = scanner;
    }

    public void manageBooks() {
        while (true) {
            clearConsole();
            System.out.println("============ Manage Books ============");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Search Book");
            System.out.println("5. View All Books");
            System.out.println("6. Back to Home");
            System.out.print("Select an option: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1": bookService.addBook(); break;
                case "2": bookService.updateBook(); break;
                case "3": bookService.deleteBook(); break;
                case "4": bookService.searchBook(); break;
                case "5": bookService.viewAllBooks(); break;
                case "6": return;
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