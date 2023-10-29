package a1_2001040077;

import common.Genre;

public class Book {
    private String ISBN;
    private String title;
    private String author;
    private Genre genre;
    private int yearPublished;
    private int numCopiesAvailable;

    public Book(String title, String author, Genre genre, int yearPublished, int numCopiesAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearPublished = yearPublished;
        this.numCopiesAvailable = numCopiesAvailable;
        this.ISBN = generateISBN();
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

     public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

     public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

     public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public void setNumCopiesAvailable(int numCopiesAvailable) {
        this.numCopiesAvailable = numCopiesAvailable;
    }

    public String generateISBN() {
        int ord = 0;
        for (int i = 0; i < Genre.values().length; i++) {
            if (Genre.values()[i].equals(genre)) {
                ord = i + 1;
                break;
            }
        } if (ord <= 9) {
            return shortName(author) + "-0" + ord + "-" + yearPublished;
        }return shortName(author) + "-" + ord + "-" + yearPublished;
    }


    public int getNumCopiesAvailable() {
        //return number of copies available
        return numCopiesAvailable;
    }

    private String shortName(String author) {
        String[] nameParts = author.split(" ");
        StringBuilder initials = new StringBuilder();
        for (String part : nameParts) {
            if (!part.isEmpty()) {
                initials.append(part.charAt(0));
            }
        }
        return initials.toString().toUpperCase();
    }
}
