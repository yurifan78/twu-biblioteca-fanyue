package com.twu.Biblioteca;

public class Book {
    protected String title;
    protected String author;
    protected String year;
    protected StatusOfBook status = StatusOfBook.INSTOCK;

    public Book(String title, String author, String year, StatusOfBook INSTOCK) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
}
