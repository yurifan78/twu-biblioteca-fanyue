package com.twu.Biblioteca;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    public List<Book> books;

    //get the book list from current book database.
    private List<Book> getBookList() {
        List<Book> bookList= new ArrayList<>();

        File booksCsv = new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource("books.csv")).getFile());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(booksCsv))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] book = row.split(",");
                Book books = new Book(book[0], book[1], book[2], book[3]);
                bookList.add(books);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    // generate the book list to display for the customer.
    protected String generateBookList() {
        List<Book> bookListWithoutCheckOut;
        List<Book> bookList = getBookList();
        bookListWithoutCheckOut = bookList.stream().filter(book -> !book.status.equals(" CHECKOUT")).collect(Collectors.toList());
        return getString(bookListWithoutCheckOut);
    }

    private String getString(List<Book> bookList) {
        StringBuilder bookListString = new StringBuilder();
        for (Book book : bookList) {
            bookListString.append(book.title)
                    .append(" |")
                    .append(book.author)
                    .append(" |")
                    .append(book.year)
                    .append("\n");
        }
        return bookListString.toString();
    }

    protected String checkOutBook(String title) throws IOException {
        List<Book> bookList = getBookList();
        if (bookList.stream().anyMatch(book -> book.title.equals(title))) {
            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i).title.equals(title)) {
                    String author = bookList.get(i).author;
                    String year = bookList.get(i).year;

                    bookList.set(i, new Book(title, author, year, "CHECKOUT"));
                    writeToFile(bookList);
                }
            }
            return "Thank you! Enjoy the book";
        } else {
            return "Sorry, that book is not available";
        }
    }

    private void writeToFile(List<Book> bookList) throws IOException {
        File booksCsv = new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource("books.csv")).getFile());
        BufferedWriter writer = new BufferedWriter(new FileWriter(booksCsv));
        for (Book book : bookList) {
            writer.write(book.toString());
            writer.newLine();
        }
        writer.close();
    }

    protected String returnBook(String title) throws IOException {
        List<Book> bookList = getBookList();
        if (bookList.stream().anyMatch(book -> book.title.equals(title) && book.status.equals(" CHECKOUT"))) {
            for (int i = 0; i < bookList.size(); i++) {
                if (bookList.get(i).title.equals(title)) {
                    String author = bookList.get(i).author;
                    String year = bookList.get(i).year;

                    bookList.set(i, new Book(title, author, year, "INSTOCK"));
                    writeToFile(bookList);
                }
            }
            return "Thank you for returning the book";
        } else {
            return "That is not a valid book to return";
        }
    }
}

