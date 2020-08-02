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

    protected List<Book> getBookList() {
        List<Book> bookList = new ArrayList<>();
        File booksCsv = getFile("books.csv");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(booksCsv))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] books = row.split(",");
                String title = books[0].trim();
                String author = books[1].trim();
                String year = books[2].trim();
                String status = books[3].trim();
                Book book = new Book(title, author, year,
                        status.equals("INSTOCK") ? Status.INSTOCK : Status.CHECKOUT);
                bookList.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    protected List<Movie> getMovieList() {
        List<Movie> movieList = new ArrayList<>();
        File moviesCsv = getFile("movies.csv");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(moviesCsv))) {
            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] movies = row.split(",");
                String name = movies[0].trim();
                String year = movies[1].trim();
                String director = movies[2].trim();
                String rate = movies[3].trim();
                String status = movies[4].trim();
                Movie movie = new Movie(name, year, director, parseInt(rate),
                        status.equals("INSTOCK") ? Status.INSTOCK : Status.CHECKOUT);
                movieList.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    protected void writeToFile(List<Book> bookList) throws IOException {
        File booksCsv = getFile("books.csv");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(booksCsv));
        for (Book book : bookList) {
            bufferedWriter.write(book.toString());
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
