package com.twu.Biblioteca;

public enum FileName {
    BOOKS("books.csv"),
    MOVIES("movies.csv");

    private final String fileName;

    FileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
