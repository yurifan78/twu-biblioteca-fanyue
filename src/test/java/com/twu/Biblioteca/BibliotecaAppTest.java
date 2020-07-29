package com.twu.Biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {

    @Test
    public void shouldWelcomeCustomerWhenStartApp() {
        Customer customer = new Customer();
        String welcomeMessage = customer.welcome();

        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!", welcomeMessage);
    }

    @Test
    public void shouldGenerateBookList() {
        Library library = new Library();
        String bookListString = library.generateBookList();

        assertEquals("Pride and Prejudice | " + "Jane Austen | " + "2012" + "\n"
                + "Nineteen Eighty-Four | " + "George Orwell | " + "2004" + "\n"
                + "Crime and Punishment | " + "Fyodor Dostoevsky | " + "2003" + "\n"
                + "Another Country | " + "James Baldwin | " + "2001" + "\n"
                + "Mrs Dalloway | " + "Virginia Woolf | " + "2004" + "\n", bookListString);
    }
}
