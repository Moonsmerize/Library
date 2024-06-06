package Utilities;

import java.util.Date;

import Entities.*;
import Repositories.*;

public class Seeder {

    @SuppressWarnings("deprecation")
    public static void initialize() {

        // clients
        Profile clientProfile1 = new Profile("Client1", "Apellido1", new Date(1971 - 1900, 4, 19));
        Profile clientProfile2 = new Profile("Client2", "Apellido2", new Date(1965 - 1900, 1, 20));
        Profile clientProfile3 = new Profile("Client3", "Apellido3", new Date(1998 - 1900, 3, 30));
        Profile clientProfile4 = new Profile("Client4", "Apellido4", new Date(1999 - 1900, 2, 07));
        Profile clientProfile5 = new Profile("Client5", "Apellido5", new Date(1953 - 1900, 5, 01));
        Client client1 = new Client(clientProfile1, "1234", "1234");
        Client client2 = new Client(clientProfile2, "4567", "4567");
        Client client3 = new Client(clientProfile3, "6789", "6789");
        Client client4 = new Client(clientProfile4, "9987", "9987");
        Client client5 = new Client(clientProfile5, "7654", "7654");
        ClientRepository.createClient(client1);
        ClientRepository.createClient(client2);
        ClientRepository.createClient(client3);
        ClientRepository.createClient(client4);
        ClientRepository.createClient(client5);

        // Authors
        Profile authorProfile1 = new Profile("Autor1", "Apellido1", new Date(1971 - 1900, 9, 05));
        Profile authorProfile2 = new Profile("Autor2", "Apellido2", new Date(1980 - 1900, 11, 21));
        Profile authorProfile3 = new Profile("Autor3", "Apellido3", new Date(1978 - 1900, 10, 31));
        Profile authorProfile4 = new Profile("Autor4", "Apellido4", new Date(2001 - 1900, 6, 16));
        Profile authorProfile5 = new Profile("Autor5", "Apellido5", new Date(2002 - 1900, 7, 18));
        Author autor1 = new Author(authorProfile1);
        Author autor2 = new Author(authorProfile2);
        Author autor3 = new Author(authorProfile3);
        Author autor4 = new Author(authorProfile4);
        Author autor5 = new Author(authorProfile5);
        AuthorRepository.addAuthor(autor1);
        AuthorRepository.addAuthor(autor2);
        AuthorRepository.addAuthor(autor3);
        AuthorRepository.addAuthor(autor4);
        AuthorRepository.addAuthor(autor5);

        // Books
        Book libro1 = new Book("ISBN123", "Libro1", autor1, new Date(1911 - 1900, 1, 28), true);
        Book libro2 = new Book("ISBN234", "Libro2", autor2, new Date(2001 - 1900, 11, 13), true);
        Book libro3 = new Book("ISBN456", "Libro3", autor3, new Date(2022 - 1900, 03, 29), true);
        Book libro4 = new Book("ISBN678", "Libro4", autor4, new Date(2019 - 1900, 9, 19), true);
        Book libro5 = new Book("ISBN899", "Libro5", autor5, new Date(1971 - 1900, 1, 1), true);
        Book libro6 = new Book("ISBN987", "Libro6", autor5, new Date(1999 - 1900, 3, 31), true);
        Book libro7 = new Book("ISBN765", "Libro7", autor5, new Date(1996 - 1900, 7, 14), true);
        Book libro8 = new Book("ISBN543", "Libro8", autor4, new Date(1980 - 1900, 11, 21), true);
        Book libro9 = new Book("ISBN321", "Libro9", autor3, new Date(1905 - 1900, 8, 31), true);
        Book libro10 = new Book("ISBN112", "Libro10", autor1, new Date(1973 - 1900, 9, 31), true);
        Book libro11 = new Book("ISBN234", "Libro11", autor1, new Date(1972 - 1900, 1, 20), true);
        BookRepository.addBook(libro1);
        BookRepository.addBook(libro2);
        BookRepository.addBook(libro3);
        BookRepository.addBook(libro4);
        BookRepository.addBook(libro5);
        BookRepository.addBook(libro6);
        BookRepository.addBook(libro7);
        BookRepository.addBook(libro8);
        BookRepository.addBook(libro9);
        BookRepository.addBook(libro10);
        BookRepository.addBook(libro11);

        /// admins
        Profile adminProfile1 = new Profile("AdminName1", "AdminLastname1", new Date(1971 - 1900, 4, 19));
        Profile adminProfile2 = new Profile("AdminName2", "AdminLastname2", new Date(1965 - 1900, 1, 20));
        Profile adminProfile3 = new Profile("AdminName3", "AdminLastname3", new Date(1963 - 1900, 12, 06));
        Administrator admin1 = new Administrator(adminProfile1, "admin1", "admin1", true);
        Administrator admin2 = new Administrator(adminProfile2, "admin2", "admin2", true);
        Administrator admin3 = new Administrator(adminProfile3, "admin3", "admin3", true);
        AdministratorRepository.createAdmin(admin1);
        AdministratorRepository.createAdmin(admin2);
        AdministratorRepository.createAdmin(admin3);

        // printing tables
        BookRepository.printAllBooks();
        ClientRepository.printAllClients();
        AuthorRepository.printAuthors();
        AdministratorRepository.printAdmins();

        // some transactions
        System.out.println("borrowing 3 books");
        TransactionRepository.transaction("borrow", client5, libro5);
        TransactionRepository.transaction("borrow", client5, libro3);
        TransactionRepository.transaction("borrow", client5, libro2);
        System.out.println("returned book 5");
        TransactionRepository.transaction("return", client5, libro5);
        System.out.println("Borrowing book 5 again");
        TransactionRepository.transaction("borrow", client1, libro5);
        TransactionRepository.printAllTransactions();
        BookRepository.printAuthorBooks(autor3);

    }

}
