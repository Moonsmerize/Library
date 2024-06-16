package Entities;

import java.util.ArrayList;

public class Author {

    private Profile profile;
    private ArrayList<Book> books;

    public Author(Profile profile, ArrayList<Book> books) {
        this.profile = profile;
        this.books = books;
    }

    public Author(Profile profile) {
        this.profile = profile;
        this.books = new ArrayList<>();
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

}
