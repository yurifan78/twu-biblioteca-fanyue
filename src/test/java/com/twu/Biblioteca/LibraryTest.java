package com.twu.Biblioteca;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LibraryTest {
    Customer customer = new Customer("fanyue",
            "yue.fan@thoughtworks.com",
            "15200002670",
            "000-2333",
            "2333");

    @Test
    public void shouldWelcomeCustomerWhenStartApp() {
        Library library = new Library();
        String welcomeMessage = library.welcome();

        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!",
                welcomeMessage);
    }

    @Test
    public void shouldAuthenticateUser() {
        Library library = new Library();

        assertTrue(library.userAuthentication(customer, customer.getId(), customer.getPassword()));
    }

    @Test
    public void shouldGenerateBookList() {
        Library library = new Library();
        String bookList = library.generateList("books.csv");
        String movieList = library.generateList("movies.csv");
        // test generate movie list
        System.out.println(movieList);

        assertEquals("Pride and Prejudice | Jane Austen | 2012"
                + "\nNineteen Eighty-Four | George Orwell | 2004"
                + "\nCrime and Punishment | Fyodor Dostoevsky | 2003"
                + "\nAnother Country | James Baldwin | 2001"
                + "\nMrs Dalloway | Virginia Woolf | 2004", bookList);
    }

    @Test
    public void shouldSendMessageWhenCheckOut() throws IOException {
        Library library = new Library();

        String title = "Pride and Prejudice";
        String titleDoesNotExist = "Java Fundamentals";
        String messageWhenCheckOutSuccess = library.checkOutBook(title, customer);
        String messageWhenCheckOutFail = library.checkOutBook(titleDoesNotExist, customer);

        assertEquals("Thank you! Enjoy the book", messageWhenCheckOutSuccess);
        assertEquals("Sorry, that book is not available", messageWhenCheckOutFail);
    }

    @Test
    public void shouldGenerateBooksInStockAfterCheckOut() throws IOException {
        Library library = new Library();

        String title = "Pride and Prejudice";
        library.checkOutBook(title, customer);
        String bookList = library.generateList("books.csv");
        // test movie checkout
        String movieTitle = "The Shawshank Redemption";
        System.out.println(library.checkOutMovie(movieTitle));
        System.out.println(library.generateList("movies.csv"));

        assertEquals("Nineteen Eighty-Four | George Orwell | 2004"
                + "\nCrime and Punishment | Fyodor Dostoevsky | 2003"
                + "\nAnother Country | James Baldwin | 2001"
                + "\nMrs Dalloway | Virginia Woolf | 2004", bookList);
    }

    @Test
    public void shouldKnowBooksCheckedOut() throws IOException {
        Library library = new Library();
        String title = "Pride and Prejudice";
        String messageWhenCheckOutSuccess = library.checkOutBook(title, customer);
        Map<Customer, String> map = new HashMap<>();
        map.put(customer, title);

        assertEquals("Thank you! Enjoy the book", messageWhenCheckOutSuccess);
        assertEquals(map, library.booksCheckedOutMap);
    }

    @Test
    public void shouldSendMessageWhenReturnBook() throws IOException {
        Library library = new Library();

        String title = "Nineteen Eighty-Four";
        String titleDoesNotExist = "Java Fundamentals";
        String checkoutMessage = library.checkOutBook(title, customer);
        String returnMessageWhenSuccess = library.returnBook(title);
        String returnMessageWhenFail = library.returnBook(titleDoesNotExist);

        assertEquals("Thank you! Enjoy the book", checkoutMessage);
        assertEquals("Thank you for returning the book", returnMessageWhenSuccess);
        assertEquals("That is not a valid book to return", returnMessageWhenFail);
    }

    @Test
    public void shouldGenerateBooksInStockAfterReturn() throws IOException {
        Library library = new Library();

        String title = "Pride and Prejudice";
        library.checkOutBook(title, customer);
        library.returnBook(title);
        String bookList = library.generateList("books.csv");

        assertEquals("Pride and Prejudice | Jane Austen | 2012"
                + "\nNineteen Eighty-Four | George Orwell | 2004"
                + "\nCrime and Punishment | Fyodor Dostoevsky | 2003"
                + "\nAnother Country | James Baldwin | 2001"
                + "\nMrs Dalloway | Virginia Woolf | 2004", bookList);
    }

    @Test
    public void shouldGeneratePersonalInfo() {
        Library library = new Library();

        String personalInfo = library.personalInfo(customer);

        assertEquals("name: fanyue"
                + "\nemail: yue.fan@thoughtworks.com"
                + "\nphone: 15200002670" , personalInfo);
    }

}
