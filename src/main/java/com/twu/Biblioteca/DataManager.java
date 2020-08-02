package com.twu.Biblioteca;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class DataManager {

    private File getFile(String name) {
        return new File(Objects.requireNonNull(getClass()
                .getClassLoader()
                .getResource(name))
                .getFile());
    }

    protected List<Item> getList(String fileName) {
        List<Item> list = new ArrayList<>();
        File booksCsv = getFile(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(booksCsv))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                Item item = getItem(row);
                list.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private Item getItem(String row) {
        String[] books = row.split(",");
        String title = books[0].trim();
        String author = books[1].trim();
        String year = books[2].trim();
        String status = books[3].trim();
        return new Book(title, author, year,
                status.equals("INSTOCK") ? Status.INSTOCK : Status.CHECKOUT);
    }

    private Movie getMovie(String row) {
        String[] movies = row.split(",");
        String name = movies[0].trim();
        String year = movies[1].trim();
        String director = movies[2].trim();
        String rate = movies[3].trim();
        String status = movies[4].trim();
        return new Movie(name, year, director, parseInt(rate),
                status.equals("INSTOCK") ? Status.INSTOCK : Status.CHECKOUT);
    }

    protected void writeToFile(List<Item> bookList) throws IOException {
        File booksCsv = getFile("books.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(booksCsv));
        for (Item book : bookList) {
            bufferedWriter.write(book.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
