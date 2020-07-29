package com.twu.Biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Library {
    public String generateBookList() {
        List<List<String>> bookList= new ArrayList<>();
        File booksCsv = new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource("books.csv")).getFile());
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(booksCsv))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                bookList.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getString(bookList);
    }

    private String getString(List<List<String>> bookList) {
        StringBuilder bookListString = new StringBuilder();
        for (List<String> book : bookList) {
            bookListString.append(book.get(0))
                    .append(" |")
                    .append(book.get(1))
                    .append(" |")
                    .append(book.get(2))
                    .append("\n");
        }
        return bookListString.toString().replaceAll("\"", "");
    }
}

