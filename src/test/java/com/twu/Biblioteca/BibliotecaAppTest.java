package com.twu.Biblioteca;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {

    @Test
    public void shouldWelcomeCustomerWhenStartApp() {
        Library library = new Library();
        String welcomeMessage = library.welcome();

        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!", welcomeMessage);
    }

    @Test
    public void shouldGenerateBookList() {
        Library library = new Library();
        String bookListString = library.generateBookList();

        assertEquals("Pride and Prejudice | Jane Austen | 2012" + "\n"
                + "Nineteen Eighty-Four | George Orwell | 2004" + "\n"
                + "Crime and Punishment | Fyodor Dostoevsky | 2003" + "\n"
                + "Another Country | James Baldwin | 2001" + "\n"
                + "Mrs Dalloway | Virginia Woolf | 2004", bookListString);
    }

    @Test
    public void shouldSendMessageWhenCheckOut() throws IOException {
        Library library = new Library();
        String title = "Pride and Prejudice";
        String titleDoesNotExist = "Java Fundamentals";
        String message = library.checkOutBook(title);
        String messageWhenCheckoutFail = library.checkOutBook(titleDoesNotExist);

        assertEquals("Thank you! Enjoy the book", message);
        assertEquals("Sorry, that book is not available", messageWhenCheckoutFail);
    }

    @Test
    public void shouldGenerateBooksInStockAfterCheckOut() throws IOException {
        Library library = new Library();
        String title = "Pride and Prejudice";
        library.checkOutBook(title);
        String bookListString = library.generateBookList();

        assertEquals("Nineteen Eighty-Four | George Orwell | 2004" + "\n"
                + "Crime and Punishment | Fyodor Dostoevsky | 2003" + "\n"
                + "Another Country | James Baldwin | 2001" + "\n"
                + "Mrs Dalloway | Virginia Woolf | 2004", bookListString);
    }

    @Test
    public void shouldSendMessageWhenReturnBook() throws IOException {
        Library library = new Library();
        String title = "Nineteen Eighty-Four";
        String titleDoesNotExist = "Java Fundamentals";
        String checkoutMessage = library.checkOutBook(title);
        String returnMessage = library.returnBook(title);
        String returnMessageWhenFail = library.returnBook(titleDoesNotExist);

        assertEquals("Thank you! Enjoy the book", checkoutMessage);
        assertEquals("Thank you for returning the book", returnMessage);
        assertEquals("That is not a valid book to return", returnMessageWhenFail);
    }

    @Test
    public void shouldGenerateBooksInStockAfterReturn() throws IOException {
        Library library = new Library();
        String title = "Pride and Prejudice";
        library.checkOutBook(title);
        library.returnBook(title);
        String bookListString = library.generateBookList();

        assertEquals("Pride and Prejudice | Jane Austen | 2012" + "\n"
                + "Nineteen Eighty-Four | George Orwell | 2004" + "\n"
                + "Crime and Punishment | Fyodor Dostoevsky | 2003" + "\n"
                + "Another Country | James Baldwin | 2001" + "\n"
                + "Mrs Dalloway | Virginia Woolf | 2004", bookListString);
    }
}
