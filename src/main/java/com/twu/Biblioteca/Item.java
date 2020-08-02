package com.twu.Biblioteca;

public class Item {
    protected String title;
    protected String year;
    protected String author;
    protected Status status;

    public Item(String title, String year, String author, Status status) {
        this.title = title;
        this.year = year;
        this.author = author;
        this.status = status;
    }

    public Item() {

    }

    public Status getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }
}
