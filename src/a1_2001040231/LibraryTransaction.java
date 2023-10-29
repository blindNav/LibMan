package a1_2001040231;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryTransaction {
    private Patron patron;
    private Book book;
    private Date checkoutDate;
    private Date dueDate;
    private Date returnDate;
    private double fineAmount;

    public LibraryTransaction(Patron patron, Book book, Date checkoutDate, Date dueDate, Date returnDate, double fineAmount) {
        this.patron = patron;
        this.book = book;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
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

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    // calculate the fine amount
    public String calculateFine() {
        if (calculateTheOverdueDate(dueDate, returnDate) > 0 && calculateTheOverdueDate(dueDate, returnDate) <= 7) {
            fineAmount = 1;
            fineAmount *= calculateTheOverdueDate(dueDate, returnDate);
        } else if (calculateTheOverdueDate(dueDate, returnDate) <= 14) {
            fineAmount = 2;
            fineAmount *= calculateTheOverdueDate(dueDate, returnDate);
        } else {
            fineAmount = 3;
            fineAmount *= calculateTheOverdueDate(dueDate, returnDate);
        }
        return String.format("%.2f", fineAmount);
    }

    public String getDescription() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM dd yyyy");
        String formatCheckoutDate = sdf.format(checkoutDate);
        String formatDueDate = sdf.format(dueDate);
//        String formatReturnDate = sdf.format(returnDate);
        String formatReturnDate = returnDate == null ? "N/A" : sdf.format(returnDate);
        return "Transaction Details:\n" +
                "\tPatron ID: " + patron.getPatronId() + "\n" +
                "\tBook ISBN: " + book.getISBN() + "\n" +
                "\tCheckout Date: " + formatCheckoutDate + "\n" +
                "\tDue Date: " + formatDueDate + "\n" +
                "\tReturn Date: " + formatReturnDate + "\n" +
                "\tFine Amount: $" + fineAmount + "\n";
    }

    public int calculateTheOverdueDate(Date checkoutDate, Date dueDate) {
        // calculate the time differences in milliseconds
        double timeDifferencesMillis = dueDate.getTime() - checkoutDate.getTime();

        // calculate the number of days
        double timeDifferencesDays = timeDifferencesMillis / (1000 * 60 * 60 * 24);

        return (int) timeDifferencesDays;
    }


}
