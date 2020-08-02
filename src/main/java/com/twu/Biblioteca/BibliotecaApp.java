package com.twu.Biblioteca;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class BibliotecaApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        Library library = new Library();
        Message message = new Message();

        render(library.welcome());
        // scanner does not hold username value til ps input;
        identityAuthentication(library);

        while (true) {
            Scanner scanner = new Scanner(System.in);

            menu();
            int number = scanner.nextInt();

            if (number == 0) {
                return;
            } else if (number == 1) {
                render(library.generateList("books.csv"));

                checkOutBook(library, message);
                TimeUnit.SECONDS.sleep(1);
                returnBook(library, message);
            } else if (number == 2) {
                render(library.generateList("movies.csv"));

                checkOutMovie(library, message);

            } else {
                out.println(message.getMessageWhenSelectInvalid());
            }
            TimeUnit.SECONDS.sleep(1);
        }

    }

    private static void identityAuthentication(Library library) {
        String username;
        String password;
        do {
            String[] identity = login();
            username = identity[0];
            password = identity[1];
        } while (!library.identityCheck(username, password));
    }

    private static String[] login() {
        Scanner scanner = new Scanner(System.in);
        out.print("\nLogin" + "\n" + "your name: ");
        String username = scanner.nextLine();
        out.print("your password: ");
        String password = scanner.nextLine();

        String[] identity = new String[2];
        identity[0] = username;
        identity[1] = password;
        return identity;
    }

    private static void render(String string) {
        out.print("\n");
        out.println(string);
    }

    private static void menu() {
        out.print("\nMenu:"
                + "\n"
                + "List of Books (Please enter number 1)"
                + "\n"
                + "List of Movies (Please enter number 2)"
                + "\n"
                + "Quit (Please enter number 0)"
                + "\n"
                + "Please enter your number:");
    }

    private static void returnBook(Library library, Message message) throws IOException {
        String messageOfReturn;
        do {
            out.print("\n");
            out.print("Please enter title of book to return:");
            String titleOfReturned = new Scanner(System.in).nextLine();
            messageOfReturn = library.returnBook(titleOfReturned);
            out.println(messageOfReturn);
        } while (messageOfReturn.equals(message.getMessageWhenReturnFail()));
    }

    private static void checkOutMovie(Library library, Message message) throws IOException {
        String messageOfCheckout;
        do {
            out.print("\n");
            out.print("Please enter title of movie to checkout:");
            String name = new Scanner(System.in).nextLine();
            messageOfCheckout = library.checkOutMovie(name);
            out.println(messageOfCheckout);
        } while (messageOfCheckout.equals(message.getMessageWhenCheckOutMovieFail()));
    }

    private static void checkOutBook(Library library, Message message) throws IOException {
        String messageOfCheckout;
        do {
            out.print("\n");
            out.print("Please enter title of book to checkout:");
            String title = new Scanner(System.in).nextLine();
            messageOfCheckout = library.checkOutBook(title);
            out.println(messageOfCheckout);
        } while (messageOfCheckout.equals(message.getMessageWhenCheckOutFail()));
    }
}
