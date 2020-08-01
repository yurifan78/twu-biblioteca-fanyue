package com.twu.Biblioteca;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;

public class BibliotecaApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        Library library = new Library();
        render(library.welcome());

        while (true) {
            Scanner scanner = new Scanner(System.in);
            Message message = new Message();

            menu();
            int number = scanner.nextInt();

            if (number == 0) {
                return;
            } else if (number == 1) {
                render(library.generateBookList());

                checkOutBook(library, message);
                TimeUnit.SECONDS.sleep(1);
                returnBook(library, message);
            } else {
                out.println(message.getMessageWhenSelectInvalid());
            }
            TimeUnit.SECONDS.sleep(1);
        }
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
                + "Quit (Please enter number number 0)"
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
