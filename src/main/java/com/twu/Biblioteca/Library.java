package com.twu.Biblioteca;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Library {

    Message message = new Message();

    protected String welcome() {
        return message.getWelcomeMessage();
    }

    protected String generateBookList() {
        DataManager bookDataManager = new DataManager();
        List<Book> bookList = bookDataManager.getBookList();
        List<Book> bookInStock = bookList.stream()
                .filter(book -> book.getStatus().equals(Status.INSTOCK))
                .collect(Collectors.toList());
        return getBookString(bookInStock);
    }

    protected String generateMovieList() {
        DataManager dataManager = new DataManager();
        List<Movie> movieList = dataManager.getMovieList();
        List<Movie> movieInStock = movieList.stream()
                .filter(book -> book.getStatus().equals(Status.INSTOCK))
                .collect(Collectors.toList());
        return getMovieString(movieInStock);
    }

    private String getMovieString(List<Movie> movieList) {
        StringBuilder movieListString = new StringBuilder();
        for (Movie movie : movieList) {
            movieListString.append(movie.getName())
                    .append(" | ")
                    .append(movie.getYear())
                    .append(" | ")
                    .append(movie.getDirector())
                    .append(" | ")
                    .append(movie.getRate())
                    .append("\n");
        }
        return movieListString.toString().trim();
    }

    private String getBookString(List<Book> bookList) {
        StringBuilder bookListString = new StringBuilder();
        for (Book book : bookList) {
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
        List<Book> bookList = bookDataManager.getBookList();

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

    private boolean findBookWithTitle(String title, Book book) {
        return book.getTitle().toLowerCase().equals(title.toLowerCase().trim());
    }

    protected String returnBook(String title) throws IOException {
        DataManager bookDataManager = new DataManager();
        List<Book> bookList = bookDataManager.getBookList();

        if (bookList.stream().anyMatch(book ->
                findBookWithTitle(title, book)
                && book.getStatus().equals(Status.CHECKOUT))) {
            for (int i = 0; i < bookList.size(); i++) {
                Book book = bookList.get(i);
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

