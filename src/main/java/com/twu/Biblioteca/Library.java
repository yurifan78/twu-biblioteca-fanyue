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
        BookDataManager bookDataManager = new BookDataManager();
        List<Book> bookList = bookDataManager.getBookList();
        List<Book> bookInStock = bookList.stream()
                .filter(book -> book.getStatus().equals(BookStatus.INSTOCK))
                .collect(Collectors.toList());
        return getString(bookInStock);
    }

    private String getString(List<Book> bookList) {
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
        BookDataManager bookDataManager = new BookDataManager();
        List<Book> bookList = bookDataManager.getBookList();

        if (bookList.stream().anyMatch(book -> book.getTitle().equals(title))) {
            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i).getTitle().equals(title)) {
                    String author = bookList.get(i).getAuthor();
                    String year = bookList.get(i).getYear();

                    bookList.set(i, new Book(title, author, year, BookStatus.CHECKOUT));
                    bookDataManager.writeToFile(bookList);
                }
            }
            return message.getMessageWhenCheckOutSuccess();
        } else {
            return message.getMessageWhenCheckOutFail();
        }
    }

    protected String returnBook(String title) throws IOException {
        BookDataManager bookDataManager = new BookDataManager();
        List<Book> bookList = bookDataManager.getBookList();

        if (bookList.stream().anyMatch(book -> book.getTitle().equals(title)
                && book.getStatus().equals(BookStatus.CHECKOUT))) {
            for (int i = 0; i < bookList.size(); i++) {
                Book book = bookList.get(i);
                if (book.getTitle().equals(title)) {
                    book.setStatus(BookStatus.INSTOCK);
                    bookDataManager.writeToFile(bookList);
                }
            }
            return message.getMessageWhenReturnSuccess();
        } else {
            return message.getMessageWhenReturnFail();
        }
    }
}

