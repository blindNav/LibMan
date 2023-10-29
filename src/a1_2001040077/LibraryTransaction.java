package a1_2001040077;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LibraryTransaction {

    private String transactionID;
    private Patron patron;
    private Book book;
    private Date dateCheckedOut;
    private Date dateDue;
    private Date dateReturned;
    private double fineAmount;

    public LibraryTransaction(Patron patron, Book book, Date dateCheckedOut, Date dateDue, Date dateReturned) {
        this.patron = patron;
        this.book = book;
        this.dateCheckedOut = dateCheckedOut;
        this.dateReturned = dateReturned;
        this.dateDue = dateDue;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDateCheckedOut() {
        return dateCheckedOut;
    }

    public void setDateCheckedOut(Date dateCheckedOut) {
        this.dateCheckedOut = dateCheckedOut;
    }

    public Date getDateDue() {
        return dateDue;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public double calculateFine() {
        if (dateReturned == null) {
            return 0;
        } else if (dateReturned.after(dateDue)) {
            double timeDifferencesMillis = dateReturned.getTime() - dateDue.getTime();
            double timeDifferencesDays = timeDifferencesMillis / (1000 * 60 * 60 * 24);
            double fine;
            if (timeDifferencesDays == 0){
                fine = 0;
            } else if (timeDifferencesDays >= 1 && timeDifferencesDays <= 7){
                fine = timeDifferencesDays;
            } else if (timeDifferencesDays <= 14){
                fine = timeDifferencesDays * 2;
            } else fine = timeDifferencesDays * 3;
            return fine;
        } else return 0;
    }

    public String getDescription() {
        //return description
        if (dateReturned == null) {
            return "\nTransaction Details:\n" +
                    "Patron ID: " + patron.getPatronID() +"\n" +
                    "Book ISBN: "+ book.getISBN() +"\n" +
                    "Checkout Date: "+ dateCheckedOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() +"\n" +
                    "Due Date: "+ dateDue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() +"\n" +
                    "Return Date: Not yet returned" +"\n" +
                    "Fine Amount: Not yet calculated";
        } else {
            double fine = calculateFine();
            return "\nTransaction Details:\n" +
                    "Patron ID: " + patron.getPatronID() + "\n" +
                    "Book ISBN: " + book.getISBN() + "\n" +
                    "Checkout Date: " + dateCheckedOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() + "\n" +
                    "Due Date: " + dateDue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() + "\n" +
                    "Return Date: " + dateReturned.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() + "\n" +
                    "Fine Amount: $" + fine;
        }
    }
}
