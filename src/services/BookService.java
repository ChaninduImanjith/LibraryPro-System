package services;

import entities.Book;
import repositories.BookRepository;
import repositories.IssuedRepository;
import java.util.Scanner;

public class BookService {
    private BookRepository bookRepository;
    private IssuedRepository issuedRepository;
    private Scanner scanner;

    public BookService(BookRepository bookRepository, IssuedRepository issuedRepository, Scanner scanner) {
        this.bookRepository = bookRepository;
        this.issuedRepository = issuedRepository;
        this.scanner = scanner;
    }

    public void addBook() {
        System.out.println("============ Add New Book ============");

        String bookId;
        while (true) {
            System.out.print("Enter Book ID: ");
            bookId = scanner.nextLine();

            if (bookRepository.isBookIdExists(bookId)) {
                System.out.println("Book ID already exists. Please enter a different ID.");
            } else {
                break;
            }
        }

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();

        int quantity = 0;
        while (true) {
            try {
                System.out.print("Enter Quantity: ");
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity <= 0) {
                    System.out.println("Quantity must be a positive integer.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        Book book = new Book(bookId, title, author, genre, quantity);
        bookRepository.addBook(book);

        System.out.println("\nBook added successfully! Press Enter to continue...");
        scanner.nextLine();
    }

    public void updateBook() {
        System.out.println("============ Update Book ============");

        System.out.print("Enter Book ID to update: ");
        String bookId = scanner.nextLine();
        Book book = bookRepository.findBookById(bookId);

        if (book == null) {
            System.out.println("\nBook not found. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        System.out.println("\nCurrent Book Details:");
        System.out.println("Book ID: " + book.getBookId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("Quantity: " + book.getQuantity());

        System.out.println("\nEnter new details (leave blank to keep current value):");

        System.out.print("Title [" + book.getTitle() + "]: ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            book.setTitle(title);
        }

        System.out.print("Author [" + book.getAuthor() + "]: ");
        String author = scanner.nextLine();
        if (!author.isEmpty()) {
            book.setAuthor(author);
        }

        System.out.print("Genre [" + book.getGenre() + "]: ");
        String genre = scanner.nextLine();
        if (!genre.isEmpty()) {
            book.setGenre(genre);
        }

        while (true) {
            System.out.print("Quantity [" + book.getQuantity() + "]: ");
            String quantityStr = scanner.nextLine();
            if (quantityStr.isEmpty()) {
                break;
            }
            try {
                int quantity = Integer.parseInt(quantityStr);
                if (quantity <= 0) {
                    System.out.println("Quantity must be a positive integer.");
                } else {
                    book.setQuantity(quantity);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        System.out.println("\nBook updated successfully! Press Enter to continue...");
        scanner.nextLine();
    }

    public void deleteBook() {
        System.out.println("============ Delete Book ============");

        System.out.print("Enter Book ID to delete: ");
        String bookId = scanner.nextLine();
        Book book = bookRepository.findBookById(bookId);

        if (book == null) {
            System.out.println("\nBook not found. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        if (issuedRepository.isBookIssued(bookId)) {
            System.out.println("\nCannot delete book. It is currently issued to a member. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        bookRepository.deleteBook(book);
        System.out.println("\nBook deleted successfully! Press Enter to continue...");
        scanner.nextLine();
    }

    public void searchBook() {
        System.out.println("============ Search Book ============");

        System.out.print("Enter Book ID to search: ");
        String bookId = scanner.nextLine();
        Book book = bookRepository.findBookById(bookId);

        if (book == null) {
            System.out.println("\nBook not found. Press Enter to continue...");
            scanner.nextLine();
            return;
        }

        System.out.println("\nBook Details:");
        System.out.println("Book ID: " + book.getBookId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Genre: " + book.getGenre());
        System.out.println("Quantity: " + book.getQuantity());

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void viewAllBooks() {
        System.out.println("============ All Books ============");

        var books = bookRepository.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("\nNo books available in the catalog.");
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            return;
        }

        System.out.printf("\n%-10s %-30s %-20s %-15s %-10s\n",
                "Book ID", "Title", "Author", "Genre", "Quantity");
        System.out.println("-------------------------------------------------------------------------------");

        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}