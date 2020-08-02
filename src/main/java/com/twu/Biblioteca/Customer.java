package com.twu.Biblioteca;

public class Customer {
    private final String name;
    private final String email;
    private final String phone;

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
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

    @Override
    public String toString() {
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
