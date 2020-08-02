package com.twu.Biblioteca;

public class Item {
    protected String title;
    protected String year;
    protected String author;
    protected Status status;
    protected int rate;

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

    public int getRate() {
        return rate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
