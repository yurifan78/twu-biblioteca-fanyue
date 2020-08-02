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

    // identify whether the item is a book or a movie.
    private Item getItem(String row) {
        String[] items = row.split(",");
        if (items.length == 4) {
            String title = items[0].trim();
            String author = items[1].trim();
            String year = items[2].trim();
            String status = items[3].trim();
            return new Book(title, author, year,
                    getInStock(status));
        } else if (items.length == 5) {
            String name = items[0].trim();
            String year = items[1].trim();
            String director = items[2].trim();
            String rate = items[3].trim();
            String status = items[4].trim();
            return new Movie(name, year, director, parseInt(rate),
                    getInStock(status));
        }
        return null;
    }

    private Status getInStock(String status) {
        return status.equals("INSTOCK") ? Status.INSTOCK : Status.CHECKOUT;
    }

    protected void writeToFile(List<Item> list, String fileName) throws IOException {
        File csv = getFile(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(csv));
        for (Item item : list) {
            bufferedWriter.write(item.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
