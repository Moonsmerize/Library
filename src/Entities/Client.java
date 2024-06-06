package Entities;

import java.util.ArrayList;

public class Client extends User {

    private ArrayList<Book> borrowedBooks;

    public Client(Profile profile, String usermane, String password) {
        super(profile, usermane, password);
        this.borrowedBooks = new ArrayList<>();
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

}
