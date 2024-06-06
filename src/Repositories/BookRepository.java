package Repositories;

import java.util.ArrayList;

import Entities.Book;
import Entities.Client;
import Entities.Author;
import Main.*;
import Utilities.DateFormatter;

public class BookRepository {

        private static ArrayList<Book> books = new ArrayList<>();

        public static void addBook(Book book) {
                books.add(book);
                book.getAuthor().getBooks().add(book);
        }

        public static void deleteBookByIndex(int index) {
                if (getBookByIndex(index).getIsAvailable() == true) {
                        getBookByIndex(index).getAuthor().getBooks().remove(getBookByIndex(index));
                        BookRepository.getBooks().remove(BookRepository.getBookByIndex(index));
                        System.out.println("\nBook successfully removed");
                } else {
                        System.out.println("\nSomething went wrong!!!");
                }
        }

        public static Book getBookByIndex(int index) {
                return books.get(index);
        }

        public static Book getBookByIsbn(String isbn) {
                for (Book book : books) {
                        if (book.getIsbn().equals(isbn)) {
                                return book;
                        }
                }
                return null;
        }

        public static void printAllBooks() {
                int index = 0;
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------------\n");
                System.out.printf(
                                "|                                               BOOKS                                                   |\n");
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------------\n");
                System.out.printf("| %18s | %20s | %20s | %15s | %10s | %3s |\n", "ISBN", "TITLE", "AUTHOR",
                                "PUBLISH DATE",
                                "AVAILABLE", "i");
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------------\n");
                for (Book book : books) {
                        System.out.printf("| %18s | %20s | %20s | %15s | %10s | %3s |\n", book.getIsbn(),
                                        book.getTitle(),
                                        book.getAuthor().getProfile().getName() + " "
                                                        + book.getAuthor().getProfile().getLastName(),
                                        DateFormatter.Formatt(book.getPublishDate()), book.getIsAvailable(), index);
                        index++;
                }
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------------\n\n");
        }

        public static void printAvailableBooks() {
                int index = 0;
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------------\n");
                System.out.printf(
                                "|                                           Available Books                                             |\n");
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------------\n");
                System.out.printf("| %18s | %20s | %20s | %15s | %10s | %3s |\n", "ISBN", "TITLE", "AUTHOR",
                                "PUBLISH DATE",
                                "AVAILABLE", "i");
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------------\n");
                for (Book book : books) {
                        if (book.getIsAvailable()) {
                                System.out.printf("| %18s | %20s | %20s | %15s | %10s | %3s |\n", book.getIsbn(),
                                                book.getTitle(),
                                                book.getAuthor().getProfile().getName() + " "
                                                                + book.getAuthor().getProfile().getLastName(),
                                                DateFormatter.Formatt(book.getPublishDate()), book.getIsAvailable(),
                                                index);
                                index++;
                        } else {
                                index++;
                        }
                }
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------------\n");
        }

        public static void printBorrowedBooks() {
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------\n");
                System.out.printf(
                                "|                                         Borrowed Books                                          |\n");
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------\n");
                System.out.printf("| %18s | %20s | %20s | %15s | %10s |\n", "ISBN", "TITLE", "AUTHOR", "PUBLISH DATE",
                                "AVAILABLE");
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------\n");
                for (Book book : books) {
                        if (!book.getIsAvailable()) {
                                System.out.printf("| %18s | %20s | %20s | %15s | %10s |\n", book.getIsbn(),
                                                book.getTitle(),
                                                book.getAuthor().getProfile().getName() + " "
                                                                + book.getAuthor().getProfile().getLastName(),
                                                DateFormatter.Formatt(book.getPublishDate()), book.getIsAvailable());
                        }
                }
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------\n");
        }

        public static void printClientBorrowedBooks(Client client) {
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------\n");
                System.out.printf(
                                "                                    Client Borrowed Books                                          \n");
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------\n");
                System.out.printf("| %18s | %20s | %20s | %15s | %10s |\n", "ISBN", "TITLE", "AUTHOR", "PUBLISH DATE",
                                "AVAILABLE");
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------\n");
                for (Book book : client.getBorrowedBooks()) {
                        System.out.printf("| %18s | %20s | %20s | %15s | %10s |\n", book.getIsbn(),
                                        book.getTitle(),
                                        book.getAuthor().getProfile().getName() + " "
                                                        + book.getAuthor().getProfile().getLastName(),
                                        DateFormatter.Formatt(book.getPublishDate()), book.getIsAvailable());
                }
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------\n");
        }

        public static void printClientBorrowedBooks() {
                Client client = (Client) ClientRepository.getUsers().get(Main.userIndex);
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------\n");
                System.out.printf(
                                "                                           BOOKS                                           \n");
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------\n");
                System.out.printf("| %18s | %20s | %20s | %15s | %10s |\n", "ISBN", "TITLE", "AUTHOR", "PUBLISH DATE",
                                "AVAILABLE");
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------\n");
                for (Book book : client.getBorrowedBooks()) {
                        System.out.printf("| %18s | %20s | %20s | %15s | %10s |\n", book.getIsbn(),
                                        book.getTitle(),
                                        book.getAuthor().getProfile().getName() + " "
                                                        + book.getAuthor().getProfile().getLastName(),
                                        DateFormatter.Formatt(book.getPublishDate()), book.getIsAvailable());
                }
                System.out.printf(
                                "---------------------------------------------------------------------------------------------------\n");
        }

        public static ArrayList<Book> getBooks() {
                return books;
        }

        public static void setBooks(ArrayList<Book> books) {
                BookRepository.books = books;
        }

        public static boolean checkAvailableBooks() {
                for (Book book : books) {
                        if (book.getIsAvailable() == true) {
                                return true;
                        }
                }
                return false;
        }

        public static boolean checkBookExist(String isbn) {
                for (Book book : books) {
                        if (book.getIsbn().equals(isbn) && book.getIsAvailable() == true) {
                                return true;
                        }
                }
                return false;
        }

        public static void printAuthorBooks(Author author1) {
                System.out.printf(
                                "----------------------------------------------------------------------------------\n");
                System.out.printf(
                                "|                                     Authors                                    |\n");
                System.out.printf(
                                "----------------------------------------------------------------------------------\n");
                System.out.printf("| %18s | %20s | %11s | %20s |\n", "Name", "Last name", "Birthday", "written books");
                System.out.printf(
                                "----------------------------------------------------------------------------------\n");
                for (Author author : AuthorRepository.getAuthors()) {
                        if (author1.equals(author)) {
                                System.out.printf("| %18s | %20s | %11s | %20s |\n", author.getProfile().getName(),
                                                author.getProfile().getLastName(),
                                                DateFormatter.Formatt(author.getProfile().getBirthdate()), " ");
                                for (Book book : BookRepository.getBooks()) {
                                        if (book.getAuthor().getProfile().getName()
                                                        .equals(author.getProfile().getName())
                                                        && book.getAuthor().getProfile().getLastName()
                                                                        .equals(author.getProfile().getLastName())) {
                                                System.out.printf("| %18s | %20s | %11s | %20s |\n", " ", " ", " ",
                                                                book.getTitle());

                                        }

                                }
                        }
                }
                System.out.printf(
                                "----------------------------------------------------------------------------------\n");
        }
}
