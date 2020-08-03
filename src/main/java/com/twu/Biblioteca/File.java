package com.twu.Biblioteca;

public enum File {
    BOOKS("books.csv"),
    MOVIES("movies.csv");

    private final String fileName;

    File(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
