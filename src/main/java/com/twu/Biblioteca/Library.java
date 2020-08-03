package com.twu.Biblioteca;

import com.twu.Biblioteca.Categories.Book;
import com.twu.Biblioteca.Categories.Item;
import com.twu.Biblioteca.Categories.Movie;
import com.twu.Biblioteca.Categories.Status;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Library {

    Map<Customer, Book> booksCheckedOutMap;
    Message message = new Message();

    public Library() {
        booksCheckedOutMap = new HashMap<>();
    }

    protected boolean userAuthentication(Customer customer, String id, String password) {
        return id.equals(customer.getId()) && password.equals(customer.getPassword());
    }

    protected String welcome() {
        return message.getWelcomeMessage();
    }

    protected String generateList(String fileName) {
        DataManager dataManager = new DataManager();
        List<Item> list = dataManager.getList(fileName);
        List<Item> itemsInStock = list.stream()
                .filter(item -> isItemCheckOut(item, Status.INSTOCK))
                .collect(Collectors.toList());

        if (fileName.equals(FileName.BOOKS.getFileName())) {
            return getBookString(itemsInStock);
        } else if (fileName.equals(FileName.MOVIES.getFileName())) {
            return getMovieString(itemsInStock);
        }
        return null;
    }

    private String getMovieString(List<Item> movieList) {
        StringBuilder movieListString = new StringBuilder();
        for (Item movie : movieList) {
            movieListString.append(movie.getTitle())
                    .append(" | ")
                    .append(movie.getYear())
                    .append(" | ")
                    .append(movie.getCreator())
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
                    .append(book.getCreator())
                    .append(" | ")
                    .append(book.getYear())
                    .append("\n");
        }
        return bookListString.toString().trim();
    }

    protected String checkOutBook(String title, Customer customer) throws IOException {
        DataManager dataManager = new DataManager();
        List<Item> list = dataManager.getList(FileName.BOOKS.getFileName());

        if (list.stream().anyMatch(book ->
                findItemWithTitle(title, book))) {
            for (int i = 0; i < list.size(); i++) {
                if (findItemWithTitle(title, list.get(i))) {
                    String author = list.get(i).getCreator();
                    String year = list.get(i).getYear();

                    Book book = new Book(title, author, year, Status.CHECKOUT);
                    booksCheckedOutMap.put(customer, book);
                    list.set(i, book);
                    dataManager.writeToFile(list, FileName.BOOKS.getFileName());
                }
            }
            return message.getMessageWhenCheckOutSuccess();
        } else {
            return message.getMessageWhenCheckOutFail();
        }
    }

    protected String checkOutMovie(String name) throws IOException {
        DataManager dataManager = new DataManager();
        List<Item> list = dataManager.getList(FileName.MOVIES.getFileName());

        if (list.stream().anyMatch(movie ->
                findItemWithTitle(name, movie))) {

            for (int i = 0; i < list.size(); i++) {
                if (findItemWithTitle(name, list.get(i))) {
                    String director = list.get(i).getCreator();
                    String year = list.get(i).getYear();
                    int rate = list.get(i).getRate();

                    list.set(i, new Movie(name, year, director, rate, Status.CHECKOUT));
                    dataManager.writeToFile(list, FileName.MOVIES.getFileName());
                }
            }

            return message.getMessageWhenCheckOutMovieSuccess();
        } else {
            return message.getMessageWhenCheckOutMovieFail();
        }
    }

    private boolean findItemWithTitle(String title, Item item) {
        return item.getTitle().toLowerCase().equals(title.toLowerCase().trim());
    }

    protected String returnBook(String title) throws IOException {
        DataManager bookDataManager = new DataManager();
        List<Item> bookList = bookDataManager.getList(FileName.BOOKS.getFileName());

        if (isItemExist(title, bookList)) {
            for (int i = 0; i < bookList.size(); i++) {
                Item book = bookList.get(i);
                if (findItemWithTitle(title, book)) {
                    book.setStatus(Status.INSTOCK);
                    bookDataManager.writeToFile(bookList, FileName.BOOKS.getFileName());
                }
            }
            return message.getMessageWhenReturnSuccess();
        } else {
            return message.getMessageWhenReturnFail();
        }
    }

    private boolean isItemExist(String title, List<Item> list) {
        return list.stream().anyMatch(item ->
                findItemWithTitle(title, item) && isItemCheckOut(item, Status.CHECKOUT));
    }

    private boolean isItemCheckOut(Item item, Status checkout) {
        return item.getStatus().equals(checkout);
    }

    protected String personalInfo (Customer customer) {
        return customer.getPersonalInfo();
    }
}

