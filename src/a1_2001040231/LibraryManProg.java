package a1_2001040231;

import common.Genre;
import common.PatronType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LibraryManProg {
    private static Date[] checkoutDate = new Date[]{
            new Date(2023 - 1900, Calendar.MARCH, 25),
            new Date(2023 - 1900, Calendar.MAY, 8),
            new Date(2023 - 1900, Calendar.JUNE, 1),
            new Date(2023 - 1900, Calendar.JUNE, 25),
            new Date(2023 - 1900, Calendar.AUGUST, 10)
    };

    private static Date[] dueDate = new Date[]{
            new Date(2023 - 1900, Calendar.APRIL, 25),
            new Date(2023 - 1900, Calendar.MAY, 10),
            new Date(2023 - 1900, Calendar.JUNE, 25),
            new Date(2023 - 1900, Calendar.JULY, 25),
            new Date(2023 - 1900, Calendar.SEPTEMBER, 20)
    };

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        List<LibraryTransaction> transactions = new ArrayList<>();

        LibraryManager libraryManager = new LibraryManager(books, transactions);

        // Initialize at least 10 books in the library collection.
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", Genre.FICTION, 1925, 5);
        Book book2 = new Book("The Catcher in the Rye", "J. D. Salinger", Genre.FANTASY, 1951, 5);
        Book book3 = new Book("The Grapes of Wrath", "John Steinbeck", Genre.HORROR, 1939, 5);
        Book book4 = new Book("To Kill a Mockingbird", "Harper Lee", Genre.MYSTERY, 1960, 5);
        Book book5 = new Book("The Color Purple", "Alice Walker", Genre.BIOGRAPHY, 1982, 5);
        Book book6 = new Book("Ulysses", "James Joyce", Genre.SCIENCE_FICTION, 1922, 5);
        Book book7 = new Book("Beloved", "Toni Morrison", Genre.ADVENTURE, 1987, 5);
        Book book8 = new Book("The Lord of the Rings", "J. R. R. Tolkien", Genre.FICTION, 1954, 5);
        Book book9 = new Book("1984", "George Orwell", Genre.SELF_HELP, 1949, 5);
        Book book10 = new Book("The Sound and the Fury", "William Faulkner", Genre.POETRY, 1929, 5);

        // add books to library
        libraryManager.addBook(book1);
        libraryManager.addBook(book2);
        libraryManager.addBook(book3);
        libraryManager.addBook(book4);
        libraryManager.addBook(book5);
        libraryManager.addBook(book6);
        libraryManager.addBook(book7);
        libraryManager.addBook(book8);
        libraryManager.addBook(book9);
        libraryManager.addBook(book10);


        // Initialize at least 3 patrons involving both regular and premium patrons.

        Patron patron1 = new Patron("John", new Date(2001, Calendar.JUNE, 01), "123456789", "0123456789", PatronType.REGULAR);
        Patron patron2 = new Patron("Jane", new Date(2002, Calendar.JUNE, 23), "Doe", "987654321", PatronType.REGULAR);
        Patron patron3 = new Patron("John", new Date(2000, Calendar.APRIL, 05), "Smith", "123456789", PatronType.PREMIUM);

        // Initialize and use to create 5 book transactions

        // 1. John checks out The Great Gatsby
        libraryManager.checkoutBook(patron1, book1, checkoutDate[0], dueDate[0]);

        // 2. Jane checks out The Catcher in the Rye
        libraryManager.checkoutBook(patron2, book2, checkoutDate[1], dueDate[1]);

        // 3. John checks out The Grapes of Wrath
        libraryManager.checkoutBook(patron1, book3, checkoutDate[2], dueDate[2]);

        // 4. Jane checks out To Kill a Mockingbird
        libraryManager.checkoutBook(patron2, book4, checkoutDate[3], dueDate[3]);

        // 5. John checks out The Color Purple
        libraryManager.checkoutBook(patron1, book5, checkoutDate[4], dueDate[4]);


        // Print currently checked-out books
        System.out.println("Currently checked-out books: ");
        for (LibraryTransaction transaction : libraryManager.getCheckedOutBooks(patron1)) {
            System.out.println(transaction.getBook().getTitle());
        }


        // Print list of the overdue books that are not returned yet
        System.out.println("\n \n Overdue books: ");
        for (LibraryTransaction transaction : libraryManager.getOverdueBooks()) {
            System.out.println(transaction.getBook().getTitle());
        }


        // Patron returns the book
        libraryManager.returnBook(transactions.get(0), new Date(2023 - 1900, Calendar.APRIL, 26));


        // Sort transactions by patron ID
        libraryManager.sort();


    }
}
