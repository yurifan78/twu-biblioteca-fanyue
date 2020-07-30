package com.twu.Biblioteca;

public class Book {
    protected String title;
    protected String author;
    protected String year;
    protected String status;

    public Book(String title, String author, String year, String status) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.status = status;
    }

    @Override
    public String toString() {
        return title + ", " + author + ", " + year + ", " + status;
    }
}
