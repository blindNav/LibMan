package a1_2001040077;

import common.Genre;
import common.PatronType;

import java.util.*;

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
        List<Book> shelf = new ArrayList<>();
        List<LibraryTransaction> trans = new ArrayList<>();
        LibraryManager libraryManager = new LibraryManager(shelf, trans);

        // Initialize at least 10 books in the library collection.
        Book b1 = new Book("No Longer Human", "Osamu Dazai", Genre.FANTASY, 1948, 5);
        Book b2 = new Book("Made in Abyss", "Akihito Tsukushi", Genre.FICTION, 2012, 5);
        Book b3 = new Book("The Hobbit", "J. R. R. Tolkien", Genre.FANTASY, 1937, 5);
        Book b4 = new Book("86", "Asato Asato", Genre.FICTION, 2017, 5);
        Book b5 = new Book("The Silence of the Lambs", "Thomas Harris", Genre.FICTION, 1988, 5);
        Book b6 = new Book("Mizumi", "Kawabata Yasunari", Genre.NON_FICTION, 1954, 5);
        Book b7 = new Book("The Da Vinci Code", "Dan Brown", Genre.FICTION, 2003, 5);
        Book b8 = new Book("The Alchemist", "Paulo Coelho", Genre.FICTION, 1988, 5);
        Book b9 = new Book("The Little Prince", "Antoine de Saint-Exupéry", Genre.FICTION, 1943, 5);
        Book b10 = new Book("Boy's Abyss", "Ryō Minenami", Genre.ROMANCE, 2020, 5);

        for (Book book : Arrays.asList(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10)) {
            libraryManager.addBook(book);
        }

        // Initialize at least 3 patrons involving both regular and premium patrons.
        Patron p1 = new Patron("Nguyen Van Hieu", "24/04/2002", "hieu@gmail.com", "192939283", PatronType.PREMIUM);
        Patron p2 = new Patron("Do Phuong Thao", "21/03/2002", "thao@gmail.com", "192939283", PatronType.REGULAR);
        Patron p3 = new Patron("Dao Minh Thanh", "18/06/2002", "thanh@gmail.com", "192939283", PatronType.PREMIUM);
        List<Patron> patrons = new ArrayList<>(Arrays.asList(p1, p2, p3));

        // Initialize and use to create 5 book transactions
        libraryManager.checkOutBook(p1, libraryManager.getBooks(2), checkoutDate[0], dueDate[0]);
        libraryManager.checkOutBook(p2, libraryManager.getBooks(3), checkoutDate[1], dueDate[1]);
        libraryManager.checkOutBook(p3, libraryManager.getBooks(4), checkoutDate[2], dueDate[2]);
        libraryManager.checkOutBook(p1, libraryManager.getBooks(5), checkoutDate[3], dueDate[3]);
        libraryManager.checkOutBook(p2, libraryManager.getBooks(6), checkoutDate[4], dueDate[4]);

        // Print currently checked-out books
        System.out.println("Currently checked-out books:");
        for(Patron p : patrons) {
            for (LibraryTransaction transaction : libraryManager.getCheckedOutBooks(p)) {
                System.out.println(transaction.getBook().getTitle() + " - " + p.getName());
            }
        }

        // Patron returns the book
        libraryManager.returnBook(trans.get(0), new Date(2023 - 1900, Calendar.MAY, 28));

        // Print list of the overdue books that are not returned yet
        System.out.println("\nList of the overdue books that are not returned yet:");
        for (LibraryTransaction transaction : libraryManager.getOverdueBooks()) {
            System.out.println(transaction.getBook().getTitle() + " - " + transaction.getPatron().getName());
        }

        // Patron returns the book
        libraryManager.returnBook(trans.get(1), new Date(2023 - 1900, Calendar.SEPTEMBER, 28));

        // Sort transactions by patron ID
        libraryManager.sort();
        for(LibraryTransaction transaction : trans) {
            System.out.println(transaction.getDescription());
        }
    }
}
