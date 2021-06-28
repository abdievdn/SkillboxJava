package ru.skillbox;

public class Book {

    private final String title;
    private final String author;
    private final int totalPages;
    private final int isbn;

    public Book(String title, String author, int totalPages, int isbn) {
        this.title = title;
        this.author = author;
        this.totalPages = totalPages;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getIsbn() {
        return isbn;
    }
}
