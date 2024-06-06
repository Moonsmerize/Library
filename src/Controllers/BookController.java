package Controllers;

import java.util.ArrayList;
import java.util.Date;

import Entities.Administrator;
import Entities.Book;
import Entities.MenuItem;
import Enums.Permission;
import Interfaces.Controller;
import Main.Main;
import Repositories.AuthorRepository;
import Repositories.BookRepository;
import Repositories.ClientRepository;
import Utilities.AskData;
import Utilities.Menu;
import Utilities.Validators;

@Deprecated
public class BookController implements Controller {

    public void createBook() {
        AskData.bufferCleaner();
        String isbn = AskData.inputString("ISBN", Validators.isbnValidator());
        String title = AskData.inputString("Title");
        AuthorRepository.printAuthors();
        int index = AskData.inputInteger("Index");
        int day = AskData.inputInteger("Day", Validators.dayValidator());
        int month = AskData.inputInteger("Month", Validators.monthValidator());
        int year = AskData.inputInteger("Year", Validators.yearValidator());
        Date publishDate = new Date(year, month, day);
        Book book = new Book(isbn, title, AuthorRepository.getAuthorByIndex(index), publishDate, true);
        AuthorRepository.getAuthorByIndex(index).addBookToAuthor(book, index);
        BookRepository.addBook(book);
    }

    public void deleteBookByIndex() {
        int index = AskData.inputInteger("Index");
        BookRepository.deleteBookByIndex(index);
    }

    public void modifyBookByIndex() {
        BookRepository.printAllBooks();
        int index = AskData.inputInteger("Index");
        Book book = BookRepository.getBookByIndex(index);
        book.setIsbn(AskData.inputString("Isbn", Validators.isbnValidator()));
        book.setTitle(AskData.inputString("Title", Validators.isbnValidator()));

    }

    public void printBooks() {
        Menu subMenu = new Menu();
        Controller printBooks1 = (permission) -> BookRepository.printAllBooks();
        Controller printBooks2 = (permission) -> BookRepository.printAvailableBooks();
        Controller printBooks3 = (permission) -> BookRepository.printBorrowedBooks();
        subMenu.addMenuItem(1, new MenuItem("Print All", printBooks1));
        subMenu.addMenuItem(2, new MenuItem("Print Available", printBooks2));
        subMenu.addMenuItem(3, new MenuItem("Print Borrowed", printBooks3));
        subMenu.display("Printing Menu", null);
    }

    @Override
    public void execute(ArrayList<Permission> permissions) {
        AskData.bufferCleaner();
        Menu subMenu = new Menu();
        Controller createBook = (permission) -> createBook();
        Controller printBook = (permission) -> printBooks();
        Controller modifyBook = (permission) -> modifyBookByIndex();
        Controller removeBook = (permission) -> deleteBookByIndex();
        Controller clientBorrrowedBooks = (permission) -> BookRepository.printClientBorrowedBooks();

        if (ClientRepository.getUsers().get(Main.userIndex) instanceof Administrator) {
            if (permissions.contains(Permission.WRITE)) {
                subMenu.addMenuItem(1, new MenuItem("Create Book", createBook));
                subMenu.addMenuItem(3, new MenuItem("Modify Book", modifyBook));
            }
            if (permissions.contains(Permission.READ)) {
                subMenu.addMenuItem(2, new MenuItem("Print Books", printBook));
            }
            if (permissions.contains(Permission.DELETE)) {
                subMenu.addMenuItem(4, new MenuItem("Remove Book", removeBook));
            }

        } else {
            subMenu.addMenuItem(1, new MenuItem("Print borrowed Books", clientBorrrowedBooks));
        }
        subMenu.display("Book Menu", permissions);
    }

}
