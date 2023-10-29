package a1_2001040231;

import common.Genre;

public class Book {
    private String ISBN;
    private String title;
    private String author;
    private Genre genre;
    private int publicationYear;
    private int numberOfCopiesAvailable;

    public Book(String title, String author, Genre genre, int publicationYear, int numberOfCopiesAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
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

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNumberOfCopiesAvailable() {
        return numberOfCopiesAvailable;
    }

    public void setNumberOfCopiesAvailable(int numberOfCopiesAvailable) {
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
    }

    public String generateISBN() {
        int genreCode = 0;
        for (int i = 0; i < Genre.values().length; i++) {
            if (this.genre.equals(Genre.values()[i])) {
                genreCode = i + 1;
                break;
            }
        }
        if (genreCode < 9) {
            return getAuthorInitials(author) + "-0" + genreCode + "-" + publicationYear;
        }
        return getAuthorInitials(author) + "-" + genreCode + "-" + publicationYear;
    }

    private String getAuthorInitials(String name) {
        String[] names = name.split(" ");
        StringBuilder initials = new StringBuilder();
        for (String n : names) {
            initials.append(n.charAt(0));
        }
        return initials.toString();
    }

    public int getNumsCopiesAvailable() {
        return numberOfCopiesAvailable;
    }
}
