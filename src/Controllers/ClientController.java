package Controllers;

import java.util.ArrayList;
import java.util.Date;

import Entities.Client;
import Entities.MenuItem;
import Entities.Profile;
import Enums.Permission;
import Interfaces.Controller;
import Repositories.ClientRepository;
import Utilities.AskData;
import Utilities.Menu;
import Utilities.Validators;

@Deprecated
public class ClientController implements Controller {

    public void modifyClient() {
        int index = AskData.inputInteger("Index");
        String name = AskData.inputString("Name");
        String lastname = AskData.inputString("Lastname");
        int day = AskData.inputInteger("Day", Validators.dayValidator());
        int month = AskData.inputInteger("Month", Validators.monthValidator());
        int year = AskData.inputInteger("Year", Validators.yearValidator());
        Date birthDay = new Date(year, month, day);
        ClientRepository.modifyClient(index, name, lastname, birthDay);
    }

    public void deleteCient() {
        ClientRepository.printAllClients();
        int index = AskData.inputInteger("Index");
        ClientRepository.deleteClientByIndex(index);
    }

    public void createClient() {
        AskData.bufferCleaner();
        String name = AskData.inputString("Name");
        String lastname = AskData.inputString("Lastname");
        int day = AskData.inputInteger("Day", Validators.dayValidator());
        int month = AskData.inputInteger("Month", Validators.monthValidator());
        int year = AskData.inputInteger("Year", Validators.yearValidator());
        Date birthDay = new Date(year, month, day);
        Profile profile = new Profile(name, lastname, birthDay);
        AskData.bufferCleaner();
        String username = AskData.inputString("Username");
        System.out.println(
                "Password must have at least 1Upper 1number 1special caracter and be at least 10 characters long");
        String password = AskData.inputString("Password", Validators.passwordValidator());
        Client client = new Client(profile, username, password);
        ClientRepository.createClient(client);
    }

    public void printClients() {
        AskData.bufferCleaner();
        Menu subMenu = new Menu();
        Controller printClients = (permission) -> ClientRepository.printAllClients();
        Controller printClients1 = (permission) -> ClientRepository.printClientBorrowedBooks();

        subMenu.addMenuItem(1, new MenuItem("Print CLients", printClients));
        subMenu.addMenuItem(2, new MenuItem("Print Clients Borrowed Books", printClients1));
        subMenu.display("Print Clients", null);

    }

    public Client getClientByIndex() {
        ClientRepository.printAllClients();
        int index = AskData.inputInteger("Index");
        Client client = (Client) ClientRepository.getUserByIndex(index);
        return client;
    }

    @Override
    public void execute(ArrayList<Permission> permissions) {
        AskData.bufferCleaner();
        Menu subMenu = new Menu();
        Controller createClient = (permission) -> createClient();
        Controller printClients = (permission) -> printClients();
        Controller modifyClient = (permission) -> modifyClient();
        Controller removeClient = (permission) -> deleteCient();

        if (permissions.contains(Permission.WRITE)) {
            subMenu.addMenuItem(1, new MenuItem("Create Client", createClient));
            subMenu.addMenuItem(3, new MenuItem("Modify Client", modifyClient));
        }
        if (permissions.contains(Permission.READ)) {
            subMenu.addMenuItem(2, new MenuItem("Print Clients", printClients));
        }
        if (permissions.contains(Permission.DELETE)) {
            subMenu.addMenuItem(4, new MenuItem("Remove Client", removeClient));
        }
        subMenu.display("Clients Menu", permissions);
    }

}
