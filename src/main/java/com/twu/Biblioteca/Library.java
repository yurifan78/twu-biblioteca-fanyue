package com.twu.Biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Library {
    //get the book list from current book database.
    private List<Book> getBookList() {
        List<Book> bookList= new ArrayList<>();
        File booksCsv = new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource("books.csv")).getFile());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(booksCsv))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] book = row.split(",");
                Book books = new Book(book[0], book[1], book[2]);
                bookList.add(books);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    // generate the book list to display for the customer.
    protected String generateBookList() {
        List<Book> bookList = getBookList();
        return getString(bookList);
    }

    private String getString(List<Book> bookList) {
        StringBuilder bookListString = new StringBuilder();
        for (Book book : bookList) {
            bookListString.append(book.name)
                    .append(" |")
                    .append(book.author)
                    .append(" |")
                    .append(book.year)
                    .append("\n");
        }
        return bookListString.toString().replaceAll("\"", "");
    }

    protected List<Book> checkOutBook(Book bookToCheckOut) {
        return null;
    }

    protected List<Book> returnBook(Book bookToReturn) {
        return null;
    }
}

