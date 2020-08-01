package com.twu.Biblioteca;

import java.io.IOException;
import java.util.Scanner;

public class BibliotecaApp {
    public static void main(String[] args) throws IOException {
        Library library = new Library();
        System.out.println(library.welcome());

        System.out.println("\nMenu:"
                + "\n"
                + "List of Books (Please enter number 1)"
                + "\n"
                + "Quit (Please enter number number 0)"
                + "\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please enter your number:");
            int number = scanner.nextInt();
            if (number == 0) {
                return;
            } else if (number == 1) {
                System.out.println(library.generateBookList());
                System.out.println("Checkout (Please enter number 2)"
                        +"\nReturn (Please enter number 3)");
            } else {
                System.out.println("Please select a valid option!");
            }
        }
        /*
        if (scanner.nextLine().equals("1")) {
            System.out.println(library.generateBookList());
            System.out.println("Checkout (Please enter number 2)"
                    +"\nReturn (Please enter number 3)"
                    +"\nPlease enter your choice:");
            if (scanner.nextLine().equals("2")) {
                System.out.println("\nPlease enter title of book to checkout:");
                String title = scanner.nextLine();
                String message = library.checkOutBook(title);
                System.out.println(message);
            }

            if (scanner.nextLine().equals("3")) {
                System.out.println("\nPlease enter title of book to return:");
                String title = scanner.nextLine();
                String message = library.returnBook(title);
                System.out.println(message);
            }
        } else if (scanner.nextLine().equals("0")){
            System.exit(0);
        } else {
            System.out.println("Please select a valid option:");
            new Scanner(System.in);
        }

         */
    }
}
