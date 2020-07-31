package com.twu.Biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookDataManager {

    private File getFile() {
        return new File(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResource("books.csv"))
                .getFile());
    }

    protected List<Book> getBookList() {
        List<Book> bookList = new ArrayList<>();
        File booksCsv = getFile();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(booksCsv))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] books = row.split(", ");
                String title = books[0];
                String author = books[1];
                String year = books[2];
                String status = books[3];
                Book book = new Book(title, author, year, status);
                bookList.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    protected void writeToFile(List<Book> bookList) throws IOException {
        File booksCsv = getFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(booksCsv));
        for (Book book : bookList) {
            writer.write(book.toString());
            writer.newLine();
        }
        writer.close();
    }
}
