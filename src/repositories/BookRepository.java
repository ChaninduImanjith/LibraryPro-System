package repositories;

import entities.Book;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    private List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public void updateBook(Book book) {
    }

    public void deleteBook(Book book) {
        books.remove(book);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public boolean isBookIdExists(String bookId) {
        return findBookById(bookId) != null;
    }
}