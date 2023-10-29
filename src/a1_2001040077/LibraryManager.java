package a1_2001040077;

import common.DateUtils;
import common.Genre;
import common.PatronType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryManager {
    private List<Book> books;
    private List<LibraryTransaction> transactions;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public LibraryManager(List<Book> books, List<LibraryTransaction> trans) {
        this.books = books;
        this.transactions = trans;
    }

    public void addBook(Book book) {
        //add book to books
        books.add(book);
    }

    public List<LibraryTransaction> getCheckedOutBooks(Patron patron) {
        //get a check-out list of a patron
        List<LibraryTransaction> checkedOutBooks = new ArrayList<>();
        for (LibraryTransaction transaction : transactions) {
            if (transaction.getPatron().getPatronID().equals(patron.getPatronID())) {
                checkedOutBooks.add(transaction);
            }
        }
        return checkedOutBooks;
    }

    public void checkOutBook(Patron patron, Book book, Date checkOutDate, Date dueDate) {
        //check out book
        PatronType patronType = patron.getPatronType();
        int checked = getCheckedOutBooks(patron).size();
        if (patronType == PatronType.REGULAR && checked > 3) {
            System.out.println("You have reached the maximum number of books you can check out.");
        } else if (patronType == PatronType.PREMIUM && checked > 5) {
            System.out.println("You have reached the maximum number of books you can check out.");
        } else {
            book.setNumCopiesAvailable(book.getNumCopiesAvailable() - 1);
            LibraryTransaction newTransaction = new LibraryTransaction(patron, book, checkOutDate, dueDate, null);
            transactions.add(newTransaction);
        }
    }

    public void returnBook(LibraryTransaction transaction, Date returnDate) {
        transaction.setFineAmount(transaction.calculateFine());
        transaction.setDateReturned(returnDate);
        Book book = transaction.getBook();
        book.setNumCopiesAvailable(book.getNumCopiesAvailable() + 1);
        System.out.println("\nReturned: " + book.getTitle() + " from " + transaction.getPatron().getName());
        //return book
    }

    public List<LibraryTransaction> getOverdueBooks() {
        //get a list of overdue books
        DateUtils du = new DateUtils();
        Date today = du.getCurrentDate();
        List<LibraryTransaction> overDue = new ArrayList<>();
        for (LibraryTransaction tran : transactions) {
            if (tran.getDateDue().before(today) && tran.getDateReturned() == null) {
                overDue.add(tran);
            }
        }
        return overDue;
    }

    public void sort(){
        //sort transaction by patron ID
        transactions.sort((o1, o2) -> o1.getPatron().getPatronID().compareTo(o2.getPatron().getPatronID()));
    }

    public Book getBooks(int index) {
        return books.get(index);
    }

}
