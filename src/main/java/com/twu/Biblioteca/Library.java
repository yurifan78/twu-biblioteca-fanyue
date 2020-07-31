package com.twu.Biblioteca;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Library {

    protected String welcome() {
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    protected String generateBookList() {
        BookDataManager bookDataManager = new BookDataManager();
        List<Book> bookList = bookDataManager.getBookList();
        List<Book> bookInStock = bookList.stream().filter(book -> !book.status.equals("CHECKOUT")).collect(Collectors.toList());
        return getString(bookInStock);
    }

    private String getString(List<Book> bookList) {
        StringBuilder bookListString = new StringBuilder();
        for (Book book : bookList) {
            bookListString.append(book.title)
                    .append(" | ")
                    .append(book.author)
                    .append(" | ")
                    .append(book.year)
                    .append("\n");
        }
        return bookListString.toString();
    }

    protected String checkOutBook(String title) throws IOException {
        BookDataManager bookDataManager = new BookDataManager();
        List<Book> bookList = bookDataManager.getBookList();
        if (bookList.stream().anyMatch(book -> book.title.equals(title))) {
            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i).title.equals(title)) {
                    String author = bookList.get(i).author;
                    String year = bookList.get(i).year;

                    bookList.set(i, new Book(title, author, year, "CHECKOUT"));
                    bookDataManager.writeToFile(bookList);
                }
            }
            return "Thank you! Enjoy the book";
        } else {
            return "Sorry, that book is not available";
        }
    }

    protected String returnBook(String title) throws IOException {
        BookDataManager bookDataManager = new BookDataManager();
        List<Book> bookList = bookDataManager.getBookList();
        if (bookList.stream().anyMatch(book -> book.title.equals(title) && book.status.equals("CHECKOUT"))) {
            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i).title.equals(title)) {
                    String author = bookList.get(i).author;
                    String year = bookList.get(i).year;

                    bookList.set(i, new Book(title, author, year, "INSTOCK"));
                    bookDataManager.writeToFile(bookList);
                }
            }
            return "Thank you for returning the book";
        } else {
            return "That is not a valid book to return";
        }
    }
}

