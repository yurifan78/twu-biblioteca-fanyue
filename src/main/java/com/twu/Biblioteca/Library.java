package com.twu.Biblioteca;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Library {

    Collection<Item> items;
    Message message = new Message();

    protected String welcome() {
        return message.getWelcomeMessage();
    }

    protected String generateBookList() {
        DataManager dataManager = new DataManager();
        List<Item> bookList = dataManager.getList("books.csv");

        List<Item> bookInStock = bookList.stream()
                .filter(book -> book.getStatus().equals(Status.INSTOCK))
                .collect(Collectors.toList());
        return getBookString(bookInStock);
    }

    protected String generateMovieList() {
        DataManager dataManager = new DataManager();
        List<Item> movieList = dataManager.getList("movies.csv");
        List<Item> movieInStock = movieList.stream()
                .filter(book -> book.getStatus().equals(Status.INSTOCK))
                .collect(Collectors.toList());
        return getMovieString(movieInStock);
    }

    private String getMovieString(List<Item> movieList) {
        StringBuilder movieListString = new StringBuilder();
        for (Item movie : movieList) {
            movieListString.append(movie.getTitle())
                    .append(" | ")
                    .append(movie.getYear())
                    .append(" | ")
                    .append(movie.getStatus())
                    .append(" | ")
                    .append(movie.getRate())
                    .append("\n");
        }
        return movieListString.toString().trim();
    }

    private String getBookString(List<Item> bookList) {
        StringBuilder bookListString = new StringBuilder();
        for (Item book : bookList) {
            bookListString.append(book.getTitle())
                    .append(" | ")
                    .append(book.getAuthor())
                    .append(" | ")
                    .append(book.getYear())
                    .append("\n");
        }
        return bookListString.toString().trim();
    }

    protected String checkOutBook(String title) throws IOException {
        DataManager bookDataManager = new DataManager();
        List<Item> bookList = bookDataManager.getList("books.csv");

        if (bookList.stream().anyMatch(book ->
                findBookWithTitle(title, book))) {
            for (int i = 0; i < bookList.size(); i++) {
                if (findBookWithTitle(title, bookList.get(i))) {
                    String author = bookList.get(i).getAuthor();
                    String year = bookList.get(i).getYear();

                    bookList.set(i, new Book(title, author, year, Status.CHECKOUT));
                    bookDataManager.writeToFile(bookList);
                }
            }
            return message.getMessageWhenCheckOutSuccess();
        } else {
            return message.getMessageWhenCheckOutFail();
        }
    }

    private boolean findBookWithTitle(String title, Item book) {
        return book.getTitle().toLowerCase().equals(title.toLowerCase().trim());
    }

    protected String returnBook(String title) throws IOException {
        DataManager bookDataManager = new DataManager();
        List<Item> bookList = bookDataManager.getList("books.csv");

        if (bookList.stream().anyMatch(book ->
                findBookWithTitle(title, book)
                && book.getStatus().equals(Status.CHECKOUT))) {
            for (int i = 0; i < bookList.size(); i++) {
                Item book = bookList.get(i);
                if (findBookWithTitle(title, book)) {
                    book.setStatus(Status.INSTOCK);
                    bookDataManager.writeToFile(bookList);
                }
            }
            return message.getMessageWhenReturnSuccess();
        } else {
            return message.getMessageWhenReturnFail();
        }
    }
}

