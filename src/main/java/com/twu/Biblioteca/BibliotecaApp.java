package com.twu.Biblioteca;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BibliotecaApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        Library library = new Library();
        System.out.println(library.welcome());

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nMenu:"
                    + "\n"
                    + "List of Books (Please enter number 1)"
                    + "\n"
                    + "Quit (Please enter number number 0)");
            System.out.print("Please enter your number:");
            int number = scanner.nextInt();
            if (number == 0) {
                return;
            } else if (number == 1) {
                System.out.print("\n");
                System.out.println(library.generateBookList());
                String messageOfCheckout;
                String messageOfReturn;
                do {
                    System.out.print("Please enter title of book to checkout:");
                    String title = new Scanner(System.in).nextLine();
                    messageOfCheckout = library.checkOutBook(title);
                    System.out.println(messageOfCheckout);
                } while (messageOfCheckout.equals("Sorry, that book is not available"));

                TimeUnit.SECONDS.sleep(1);

                do {
                    System.out.print("\nPlease enter title of book to return:");
                    String titleOfReturned = new Scanner(System.in).nextLine();
                    messageOfReturn = library.returnBook(titleOfReturned);
                    System.out.println(messageOfReturn);
                } while (messageOfReturn.equals("That is not a valid book to return"));
            } else {
                System.out.println("Please select a valid option!");
            }

            TimeUnit.SECONDS.sleep(1);
        }
    }
}
