package com.twu.Biblioteca.Items;

public class Movie extends Item{
    private String name;
    private String year;
    private String director;
    private int rate;
    private Status status;

    public Movie(String name, String year, String director, int rate, Status status) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rate = rate;
        this.status = status;
    }

    @Override
    public String getTitle() {
        return name;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String getAuthor() {
        return director;
    }

    public int getRate() {
        return rate;
    }

    public Status getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name +
                ", " +
                year +
                ", " +
                director +
                ", " +
                rate +
                ", " +
                status;
    }
}

