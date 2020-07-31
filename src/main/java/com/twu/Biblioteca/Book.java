package com.twu.Biblioteca;

public class Book{
    private String title;
    private String author;
    private String year;
    private BookStatus status;

    public Book(String title, String author, String year, BookStatus status) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return title +
                ", " +
                author +
                ", " +
                year +
                ", " +
                status;
    }
}
