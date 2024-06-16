package Controllers;

import java.util.ArrayList;
import java.util.Date;
import Entities.Author;
import Entities.MenuItem;
import Entities.Profile;
import Enums.Permission;
import Interfaces.Controller;
import Repositories.AuthorRepository;
import Utilities.AskData;
import Utilities.Menu;
import Utilities.Validators;

@Deprecated
public class AuthorController implements Controller {

    public void createAuthor() {
        AskData.bufferCleaner();
        String name = AskData.inputString("name");
        String lastname = AskData.inputString("Lastname");
        int day = AskData.inputInteger("Day", Validators.dayValidator());
        int month = AskData.inputInteger("Month", Validators.monthValidator());
        int year = AskData.inputInteger("Year", Validators.yearValidator());
        Date birthDay = new Date(year, month, day);
        Profile profile = new Profile(name, lastname, birthDay);
        Author author = new Author(profile);
        AuthorRepository.addAuthor(author);
    }

    public void deleteAuthorByIndex() {
        int index = AskData.inputInteger("Index");
        AuthorRepository.deleteAuthor(index);
    }

    public void modifyAuthorByIndex() {
        AuthorRepository.printAuthors();
        int index = AskData.inputInteger("Index");
        AskData.bufferCleaner();
        String name = AskData.inputString("Name");
        String lastname = AskData.inputString("Lastname");
        int day = AskData.inputInteger("Day", Validators.dayValidator());
        int month = AskData.inputInteger("Month", Validators.monthValidator());
        int year = AskData.inputInteger("Year", Validators.yearValidator());
        Date birthDay = new Date(year, month, day);
        Profile profile = new Profile(name, lastname, birthDay);
        AuthorRepository.getAuthorByIndex(index).setProfile(profile);
    }

    public void printAuthors() {
        AuthorRepository.printAuthors();
    }

    @Override
    public void execute(ArrayList<Permission> permissions) {
        AskData.bufferCleaner();
        Menu subMenu = new Menu();
        Controller createAuthor = (permission) -> createAuthor();
        Controller printAuthors = (permission) -> printAuthors();
        Controller removeAuthor = (permission) -> deleteAuthorByIndex();
        Controller modifyAuthor = (permission) -> modifyAuthorByIndex();

        if (permissions.contains(Permission.WRITE)) {
            subMenu.addMenuItem(1, new MenuItem("Create Author", createAuthor));
            subMenu.addMenuItem(4, new MenuItem("Modify Author", modifyAuthor));
        }
        if (permissions.contains(Permission.DELETE)) {
            subMenu.addMenuItem(3, new MenuItem("Remove Author", removeAuthor));
        }
        if (permissions.contains(Permission.READ)) {
            subMenu.addMenuItem(2, new MenuItem("Print Authors", printAuthors));
        }

        subMenu.display("Author Menu", permissions);
        // AskData.bufferCleaner();
    }

}
