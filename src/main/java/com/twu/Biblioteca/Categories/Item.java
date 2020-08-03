package com.twu.Biblioteca.Categories;

public class Item {
    protected String title;
    protected String year;
    protected String creator;
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

    public String getCreator() {
        return creator;
    }

    public int getRate() {
        return rate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
