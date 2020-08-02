package com.twu.Biblioteca.Items;

public class Book extends Item{
    private String title;
    private String author;
    private String year;
    private Status status;

    public Book(String title, String author, String year, Status status) {
        super();
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

    public Status getStatus() {
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

    public void setStatus(Status status) {
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
