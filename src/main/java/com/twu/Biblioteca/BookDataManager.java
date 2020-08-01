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
                String[] books = row.split(",");
                String title = books[0].trim();
                String author = books[1].trim();
                String year = books[2].trim();
                String status = books[3].trim();
                Book book = new Book(title, author, year,
                        status.equals("INSTOCK") ? BookStatus.INSTOCK : BookStatus.CHECKOUT);
                bookList.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    protected void writeToFile(List<Book> bookList) throws IOException {
        File booksCsv = getFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(booksCsv));
        for (Book book : bookList) {
            bufferedWriter.write(book.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
