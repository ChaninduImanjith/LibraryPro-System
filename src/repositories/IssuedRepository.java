package repositories;

import entities.IssuedBook;
import java.util.ArrayList;
import java.util.List;

public class IssuedRepository {
    private List<IssuedBook> issuedBooks;

    public IssuedRepository() {
        this.issuedBooks = new ArrayList<>();
    }

    public void addIssuedBook(IssuedBook issuedBook) {
        issuedBooks.add(issuedBook);
    }

    public IssuedBook findIssuedRecord(String bookId, String memberId) {
        for (IssuedBook issuedBook : issuedBooks) {
            if (issuedBook.getBookId().equals(bookId) && issuedBook.getMemberId().equals(memberId)) {
                return issuedBook;
            }
        }
        return null;
    }

    public void removeIssuedBook(IssuedBook issuedBook) {
        issuedBooks.remove(issuedBook);
    }

    public List<IssuedBook> getAllIssuedBooks() {
        return new ArrayList<>(issuedBooks);
    }

    public boolean isBookIssued(String bookId) {
        for (IssuedBook issuedBook : issuedBooks) {
            if (issuedBook.getBookId().equals(bookId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isBookIssuedToMember(String bookId, String memberId) {
        for (IssuedBook issuedBook : issuedBooks) {
            if (issuedBook.getBookId().equals(bookId) && issuedBook.getMemberId().equals(memberId)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasMemberIssuedBooks(String memberId) {
        for (IssuedBook issuedBook : issuedBooks) {
            if (issuedBook.getMemberId().equals(memberId)) {
                return true;
            }
        }
        return false;
    }

    public int countBooksIssuedToMember(String memberId) {
        int count = 0;
        for (IssuedBook issuedBook : issuedBooks) {
            if (issuedBook.getMemberId().equals(memberId)) {
                count++;
            }
        }
        return count;
    }
}