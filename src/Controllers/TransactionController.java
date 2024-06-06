package Controllers;

import java.util.ArrayList;
import java.util.Date;

import Entities.Book;
import Entities.Client;
import Entities.MenuItem;
import Enums.Permission;
import Interfaces.Controller;
import Main.Main;
import Repositories.BookRepository;
import Repositories.ClientRepository;
import Repositories.TransactionRepository;
import Utilities.AskData;
import Utilities.Menu;
import Utilities.Validators;

public class TransactionController implements Controller {

    @SuppressWarnings("deprecation")
    public void printTransactionsByDate() {
        int day, month, year;
        // date1
        day = AskData.inputInteger("Day", Validators.dayValidator());
        month = AskData.inputInteger("Month", Validators.monthValidator());
        year = AskData.inputInteger("Year", Validators.yearValidator());
        Date date1 = new Date(year, month, day);
        // date2
        day = AskData.inputInteger("Day", Validators.dayValidator());
        month = AskData.inputInteger("Month", Validators.monthValidator());
        year = AskData.inputInteger("Year", Validators.yearValidator());
        Date date2 = new Date(year, month, day);
        TransactionRepository.printTransactionsByDate(date1, date2);
    }

    public void printTransactionByClient() {
        ClientRepository.printAllClients();
        int index = AskData.inputInteger("index");
        Client client = (Client) ClientRepository.getUserByIndex(index);
        TransactionRepository.printTransactionsByClient(client);
    }

    public void printTransactionByBook() {
        BookRepository.printAllBooks();
        int index = AskData.inputInteger("index");
        Book book = BookRepository.getBookByIndex(index);
        TransactionRepository.printTransactionsByBook(book);
    }

    public void printTransactions() {
        AskData.bufferCleaner();
        Menu subMenu = new Menu();
        Controller printByRange = (permission) -> printTransactionsByDate();
        Controller printByClient = (permission) -> printTransactionByClient();
        Controller printByBook = (permission) -> printTransactionByBook();
        Controller printAll = (permission) -> TransactionRepository.printAllTransactions();
        subMenu.addMenuItem(1, new MenuItem("By range", printByRange));
        subMenu.addMenuItem(2, new MenuItem("By Client", printByClient));
        subMenu.addMenuItem(3, new MenuItem("By Book", printByBook));
        subMenu.addMenuItem(4, new MenuItem("All", printAll));
        ArrayList<Permission> permissions = null;
        subMenu.display("Printing Transactions Menu", permissions);
    }

    public void borrowABook() {
        BookRepository.printAvailableBooks();
        int bookIndex = AskData.inputInteger("Index");
        AskData.bufferCleaner();
        ClientRepository.printAvailableClients();
        int clientIndex = AskData.inputInteger("Index");
        Book book = BookRepository.getBookByIndex(bookIndex);
        Client client = (Client) ClientRepository.getUserByIndex(clientIndex);
        TransactionRepository.borrowABookByBook(client, book);
    }

    public void retrurnABook() {
        ClientRepository.printClientsWithBooks();
        int index = AskData.inputInteger("Index");
        Client client = (Client) ClientRepository.getUserByIndex(index);
        BookRepository.printClientBorrowedBooks(client);
        int bookIndex = AskData.inputInteger("Index");
        Book book = client.getBorrowedBooks().get(bookIndex);
        TransactionRepository.returnABookByBook(client, book);
    }

    @Override
    public void execute(ArrayList<Permission> permissions) {
        AskData.bufferCleaner();
        Menu subMenu = new Menu();
        Controller borrowABook = (permission) -> borrowABook();
        Controller returnABook = (permission) -> retrurnABook();
        Controller printTransactions = (permission) -> printTransactions();
        Controller printTransactionByClient = (permission) -> TransactionRepository.printTransactionsByClient();

        if (ClientRepository.getUsers().get(Main.userIndex) instanceof Client) {
            subMenu.addMenuItem(1, new MenuItem("Print Transactions done", printTransactionByClient));
        } else {
            subMenu.addMenuItem(1, new MenuItem("Borrow a Book", borrowABook));
            subMenu.addMenuItem(2, new MenuItem("Return a Book", returnABook));
            subMenu.addMenuItem(3, new MenuItem("Print Transactions", printTransactions));
        }

        subMenu.display("Transactions Menu", permissions);
    }

}
