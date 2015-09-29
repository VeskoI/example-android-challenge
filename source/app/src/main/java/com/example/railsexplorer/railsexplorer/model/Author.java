package com.example.railsexplorer.railsexplorer.model;

public class Author {

    public String name;
    public String email;

    @Override
    public String toString() {
        return name + ", " + email;
    }
}
