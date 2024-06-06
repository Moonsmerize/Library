package Repositories;

import java.util.ArrayList;

import Entities.*;
import Utilities.DateFormatter;

public class AuthorRepository {

        private static ArrayList<Author> authors = new ArrayList<>();

        public static void addAuthor(Author author) {
                authors.add(author);
        }

        public static void deleteAuthor(int index) {
                if (getAuthorByIndex(index).getBooks().isEmpty()) {
                        authors.remove(index);
                        System.out.println("Author successfully removed");
                } else {
                        System.out.println("Something went wrong");
                }

        }

        public static ArrayList<Author> getAuthors() {
                return authors;
        }

        public static void setAuthors(ArrayList<Author> authors) {
                AuthorRepository.authors = authors;
        }

        public static Author getAuthorByIndex(int index) {
                return authors.get(index);
        }

        public static void printAuthors() {
                int index = 0;
                System.out.printf(
                                "----------------------------------------------------------------------------------------\n");
                System.out.printf(
                                "|                                          Authors                                     |\n");
                System.out.printf(
                                "----------------------------------------------------------------------------------------\n");
                System.out.printf("| %18s | %20s | %11s | %20s | %3s |\n", "Name", "Last name", "Birthday",
                                "written books", "i");
                System.out.printf(
                                "----------------------------------------------------------------------------------------\n");
                for (Author author : authors) {
                        System.out.printf("| %18s | %20s | %11s | %20s | %3s |\n", author.getProfile().getName(),
                                        author.getProfile().getLastName(),
                                        DateFormatter.Formatt(author.getProfile().getBirthdate()), " ", index);
                        for (Book book : BookRepository.getBooks()) {
                                if (book.getAuthor().getProfile().getName().equals(author.getProfile().getName())
                                                && book.getAuthor().getProfile().getLastName()
                                                                .equals(author.getProfile().getLastName())) {
                                        System.out.printf("| %18s | %20s | %11s | %20s | %3s |\n", " ", " ", " ",
                                                        book.getTitle(), "");

                                }
                        }
                        index++;
                        System.out.printf(
                                        "----------------------------------------------------------------------------------------\n\n");
                }

        }

}
