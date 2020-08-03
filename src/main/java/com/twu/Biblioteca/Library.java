package com.twu.Biblioteca;

import com.twu.Biblioteca.Categories.Book;
import com.twu.Biblioteca.Categories.Item;
import com.twu.Biblioteca.Categories.Movie;
import com.twu.Biblioteca.Categories.Status;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Library {

    Map<Customer, String> booksCheckedOutMap;
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
                .filter(item -> item.getStatus().equals(Status.INSTOCK))
                .collect(Collectors.toList());

        if (fileName.equals("books.csv")) {
            return getBookString(itemsInStock);
        } else if (fileName.equals("movies.csv")) {
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
                    .append(movie.getAuthor())
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

    protected String checkOutBook(String title, Customer customer) throws IOException {
        DataManager dataManager = new DataManager();
        List<Item> list = dataManager.getList("books.csv");

        if (list.stream().anyMatch(book ->
                findItemWithTitle(title, book))) {
            for (int i = 0; i < list.size(); i++) {
                if (findItemWithTitle(title, list.get(i))) {
                    String author = list.get(i).getAuthor();
                    String year = list.get(i).getYear();

                    list.set(i, new Book(title, author, year, Status.CHECKOUT));
                    dataManager.writeToFile(list, "books.csv");
                }
            }
            booksCheckedOutMap.put(customer, title);
            return message.getMessageWhenCheckOutSuccess();
        } else {
            return message.getMessageWhenCheckOutFail();
        }
    }

    protected String checkOutMovie(String name) throws IOException {
        DataManager dataManager = new DataManager();
        List<Item> list = dataManager.getList("movies.csv");

        if (list.stream().anyMatch(movie ->
                findItemWithTitle(name, movie))) {
            for (int i = 0; i < list.size(); i++) {
                if (findItemWithTitle(name, list.get(i))) {
                    String director = list.get(i).getAuthor();
                    String year = list.get(i).getYear();
                    int rate = list.get(i).getRate();

                    list.set(i, new Movie(name, year, director, rate, Status.CHECKOUT));
                    dataManager.writeToFile(list, "movies.csv");
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
        List<Item> bookList = bookDataManager.getList("books.csv");

        if (bookList.stream().anyMatch(book ->
                findItemWithTitle(title, book)
                && book.getStatus().equals(Status.CHECKOUT))) {
            for (int i = 0; i < bookList.size(); i++) {
                Item book = bookList.get(i);
                if (findItemWithTitle(title, book)) {
                    book.setStatus(Status.INSTOCK);
                    bookDataManager.writeToFile(bookList, "books.csv");
                }
            }
            return message.getMessageWhenReturnSuccess();
        } else {
            return message.getMessageWhenReturnFail();
        }
    }

    protected String personalInfo (Customer customer) {
        return customer.getPersonalInfo();
    }
}

