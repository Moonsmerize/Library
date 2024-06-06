package Main;

import java.util.ArrayList;

import Controllers.*;
import Entities.*;
import Enums.Permission;
import Interfaces.Controller;
import Repositories.*;
import Utilities.*;

@SuppressWarnings("deprecation")
public class Main {

    public static int logoutValidator;
    public static int userIndex;

    public static void main(String[] args) {

        Seeder.initialize();

        boolean shutdown = false;
        byte loggingAttempts = 0;
        ArrayList<Permission> permissions = new ArrayList<>();
        Menu mainMenu = new Menu();
        Controller bookController = new BookController();
        Controller clientController = new ClientController();
        Controller authorController = new AuthorController();
        Controller transactionController = new TransactionController();
        Controller administratorController = new AdministratorController();
        Controller printByClient = (permission) -> TransactionRepository.printTransactionsByClient();
        Controller printBooks = (permission) -> BookRepository.printClientBorrowedBooks();
        Controller logout = new Logout();

        do {
            String username = AskData.inputString("Username");
            String password = AskData.inputString("Password");
            if (Login.login(username, password) != null) {
                loggingAttempts = 0;
                logoutValidator = -1;
                User user = Login.login(username, password);
                System.out.println("\nWelcome " + user.getUsermane() + "\n");
                if (user instanceof Administrator) {
                    Administrator administrator = (Administrator) user;
                    userIndex = ClientRepository.getUsers().indexOf(user);
                    permissions = administrator.getPermissions();
                    mainMenu.addMenuItem(0, new MenuItem("Logout", logout, permissions));
                    mainMenu.addMenuItem(1, new MenuItem("Books Menu", bookController, permissions));
                    mainMenu.addMenuItem(2, new MenuItem("Clients Menu", clientController, permissions));
                    mainMenu.addMenuItem(3, new MenuItem("Authors Menu", authorController, permissions));
                    mainMenu.addMenuItem(4, new MenuItem("Transactions Menu", transactionController, permissions));
                    if (administrator.isSuperAdmin()) {
                        mainMenu.addMenuItem(5,
                                new MenuItem("Administrators Menu", administratorController, permissions));
                    }
                } else {
                    userIndex = ClientRepository.getUsers().indexOf(user);
                    permissions.add(Permission.READ);
                    mainMenu.addMenuItem(0, new MenuItem("Logout", logout, permissions));
                    mainMenu.addMenuItem(1, new MenuItem("Print your current borrowed books", printBooks));
                    mainMenu.addMenuItem(2, new MenuItem("Print your Transacctions", printByClient));
                }
                do {
                    mainMenu.display("Menu", permissions);
                } while (logoutValidator != 0);
                user = null;
                mainMenu.getMenuItems().clear();
                System.out.println("\nYou logged out\n");
                permissions.removeAll(permissions);
                AskData.bufferCleaner();
                shutdown = !AskData.inputBoolean("Login again?");
                AskData.bufferCleaner();
            } else if (loggingAttempts < 2) {
                System.out.println("Username o password incorrect!! try again\n");
            } else {
                System.out.println("\nSYSTEM LOCKED!!!");
                shutdown = true;
            }
            loggingAttempts++;
        } while (!shutdown);

    }

}
