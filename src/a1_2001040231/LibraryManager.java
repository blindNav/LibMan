package a1_2001040231;

import common.DateUtils;
import common.PatronType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryManager {
    private List<Book> books;
    private List<LibraryTransaction> transactions;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public LibraryManager(List<Book> books, List<LibraryTransaction> transactions) {
        this.books = books;
        this.transactions = transactions;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<LibraryTransaction> getCheckedOutBooks(Patron patron) {
        List<LibraryTransaction> result = new ArrayList<>();

        // Loop through all transactions
        for (LibraryTransaction transaction : transactions) {
            if (transaction.getPatron().getPatronId().equals(patron.getPatronId())) {
                result.add(transaction);
            }
        }
        return result;
    }

    public void checkoutBook(Patron patron, Book book, Date checkoutDate, Date dueDate) {
        // first, check if the patron has exceeded their checkout limit based on their patron type
        PatronType patronType = patron.getPatronType();
        // if patron type is regular, it means patron can only checkout 3 books at a time
        if ((patronType.equals(PatronType.REGULAR) && (getCheckedOutBooks(patron).size() < 3)) || (patronType.equals(PatronType.PREMIUM) && getCheckedOutBooks(patron).size() < 5)) {
            createNewLibraryTransactionAndUpdateBook(book, patron, checkoutDate, dueDate);
        } else {
            System.out.println("Patron has exceeded their checkout limit");
        }
    }

    private void createNewLibraryTransactionAndUpdateBook(Book book, Patron patron, Date checkoutDate, Date dueDate) {
        LibraryTransaction transaction = new LibraryTransaction(patron, book, checkoutDate, dueDate, null, 0);
        transactions.add(transaction);
        // then update the number of available copies for the book
        book.setNumberOfCopiesAvailable(book.getNumberOfCopiesAvailable() - 1);
    }


    public List<LibraryTransaction> getOverdueBooks() {
        DateUtils currentDate = new DateUtils();
        List<LibraryTransaction> overdueBooks = new ArrayList<>();
        for (LibraryTransaction transaction : transactions) {
            if (transaction.getDueDate().before(currentDate.getCurrentDate())){
                overdueBooks.add(transaction);
            }
        }
        return overdueBooks;
    }

    // sort by patron id
    public List<LibraryTransaction> sort() {
        Collections.sort(transactions, new Comparator<LibraryTransaction>() {
            @Override
            public int compare(LibraryTransaction t1, LibraryTransaction t2) {
                String patronId1 = t1.getPatron().getPatronId();
                String patronId2 = t2.getPatron().getPatronId();

                // Extract the numerical part of the patron IDs
                int num1 = extractNumber(patronId1);
                int num2 = extractNumber(patronId2);

                return Integer.compare(num1, num2);
            }

            private int extractNumber(String s) {
                // Use a regular expression to extract the numeric part
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(s);

                if (matcher.find()) {
                    return Integer.parseInt(matcher.group());
                }

                // Return a default value (e.g., -1) if no number is found
                return -1;
            }
        });
        return transactions;
    }


    // This method allows a patron to return a book, calculates fines (if any), and
    //updates the number of available copies. It sets the return date in the
    //transaction, calculates fines, updates the number of available copies for the
    //book, and prints a success message.
    public Book returnBook(LibraryTransaction transaction, Date returnDate) {

        // set the return date in the transaction
        transaction.setReturnDate(returnDate);

        // calculate fines (if any)
        String fineAmount = transaction.calculateFine();
        transaction.setFineAmount(Double.parseDouble(fineAmount));

        // get book and update number of copies available
        Book currentBook = transaction.getBook();
        currentBook.setNumberOfCopiesAvailable(currentBook.getNumberOfCopiesAvailable() + 1);

        // print a success message
        System.out.println("Book returned successfully");
        return currentBook;

    }

    public List<LibraryTransaction> getTransactions() {
        return transactions;
    }
}
