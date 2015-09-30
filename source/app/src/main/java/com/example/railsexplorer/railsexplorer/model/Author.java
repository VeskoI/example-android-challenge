package com.example.railsexplorer.railsexplorer.model;

public class Author {

    public String name;
    public String email;

    @Override
    public String toString() {
        return name + ", " + email;
    }

    @Override
    public boolean equals(Object second) {
        if (second instanceof Author) {
            return this.email.equals(((Author) second).email);
        }

        return false;
    }
}
