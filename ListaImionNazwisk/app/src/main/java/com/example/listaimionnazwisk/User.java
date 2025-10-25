package com.example.listaimionnazwisk;

public class User {
    private String firstName;
    private String lastName;
    private int image;

    public User(String firstName, String lastName, int image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public int getImage() {
        return image;
    }
}