package com.twu.Biblioteca;

import com.twu.Biblioteca.Items.Book;

import java.util.List;

public class Customer {
    private final String name;
    private final String email;
    private final String phone;
    private final String id;
    private final String password;
    List<Book> bookList;

    public Customer(String name, String email, String phone, String id, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getPersonalInfo() {
        return "name: " +
                getName() +
                "\n" +
                "email: " +
                getEmail() +
                "\n" +
                "phone: " +
                getPhone();
    }
}
