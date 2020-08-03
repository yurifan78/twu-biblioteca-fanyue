package com.twu.Biblioteca;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class BibliotecaApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        Library library = new Library();
        Message message = new Message();
        Customer customer = new Customer("fanyue",
                "yue.fan@thoughtworks.com",
                "15200002670",
                "000-2333",
                "2333");

        format(library.welcome());
        // to fix: scanner does not hold username value til ps input;
        userAuthentication(customer, library);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            menu();
            int number = scanner.nextInt();

            if (number == 0) {
                return;

            } else if (number == 1) {

                format(library.generateList(File.BOOKS.getFileName()));

                checkOutBook(library, message, customer);
                TimeUnit.SECONDS.sleep(1);
                returnBook(library, message);

            } else if (number == 2) {

                format(library.generateList(File.MOVIES.getFileName()));
                checkOutMovie(library, message);

            } else if (number == 3) {

                format(library.personalInfo(customer));

            } else {

                out.println(message.getMessageWhenSelectInvalid());

            }
            TimeUnit.SECONDS.sleep(1);
        }

    }

    private static void userAuthentication(Customer customer, Library library) {
        String id;
        String password;
        do {
            String[] identity = login();
            id = identity[0];
            password = identity[1];
        } while (!library.userAuthentication(customer, id, password));
    }

    private static String[] login() {
        Scanner scanner = new Scanner(System.in);
        out.print("\nLogin" + "\n" + "your id: ");
        String id = scanner.nextLine();
        out.print("your password: ");
        String password = scanner.nextLine();

        String[] user = new String[2];
        user[0] = id;
        user[1] = password;
        return user;
    }

    private static void format(String string) {
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
                + "Personal Information (Please enter number 3)"
                + "\n"
                + "Quit (Please enter number 0)"
                + "\n"
                + "Please enter your number:");
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

    private static void checkOutBook(Library library, Message message, Customer customer) throws IOException {
        String messageOfCheckout;
        do {
            out.print("\n");
            out.print("Please enter title of book to checkout:");
            String title = new Scanner(System.in).nextLine();
            messageOfCheckout = library.checkOutBook(title, customer);
            out.println(messageOfCheckout);
        } while (messageOfCheckout.equals(message.getMessageWhenCheckOutFail()));
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
}
