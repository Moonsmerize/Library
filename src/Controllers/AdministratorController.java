package Controllers;

import java.util.ArrayList;
import java.util.Date;

import Entities.Administrator;
import Entities.MenuItem;
import Entities.Profile;
import Enums.Permission;
import Interfaces.Controller;
import Repositories.AdministratorRepository;
import Repositories.ClientRepository;
import Utilities.AskData;
import Utilities.Menu;
import Utilities.Validators;

@SuppressWarnings("deprecation")
public class AdministratorController implements Controller {

    public void modifyAdmin() {
        int index = AskData.inputInteger("Index");
        String name = AskData.inputString("Name");
        String lastname = AskData.inputString("Lastname");
        int day = AskData.inputInteger("Day", Validators.dayValidator());
        int month = AskData.inputInteger("Month", Validators.monthValidator());
        int year = AskData.inputInteger("Year", Validators.yearValidator());
        Date birthDay = new Date(year, month, day);
        ClientRepository.modifyClient(index, name, lastname, birthDay);
    }

    public void deleteAdmin() {
        AdministratorRepository.printAdmins();
        int index = AskData.inputInteger("Index");
        ClientRepository.deleteUserByIndex(index);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void createAdmin() {
        boolean permissions;
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
        Boolean isSuperAdmin = AskData.inputBoolean("Is superadmin?");
        ArrayList adminPermissions = new ArrayList<>();
        permissions = AskData.inputBoolean("Read Permissions? ");
        if (permissions)
            adminPermissions.add(Permission.READ);
        permissions = AskData.inputBoolean("Write Permissions? ");
        if (permissions)
            adminPermissions.add(Permission.WRITE);
        permissions = AskData.inputBoolean("Delete Permissions? ");
        if (permissions)
            adminPermissions.add(Permission.DELETE);
        Administrator administrator = new Administrator(profile, username, password, isSuperAdmin);
        AdministratorRepository.createAdmin(administrator);
    }

    public void printAdmins() {
        AdministratorRepository.printAdmins();
    }

    public void removePermissions() {
        AdministratorRepository.printAdmins();
        int index = AskData.inputInteger("Index");
        Administrator admin = (Administrator) ClientRepository.getUserByIndex(index);
        if (admin.getPermissions().isEmpty()) {
            System.out.println("None to do here");
        } else {
            if (admin.getPermissions().contains(Permission.WRITE)) {
                System.out.println("1 - remove write permission");
            }
            if (admin.getPermissions().contains(Permission.READ)) {
                System.out.println("2 - remove read permission");
            }
            if (admin.getPermissions().contains(Permission.DELETE)) {
                System.out.println("3 - remove delete permission");
            }
            int option = AskData.inputInteger("Option");
            if (option == 1) {
                admin.getPermissions().remove(Permission.WRITE);
                System.out.println("Permission successfully removed");
            }
            if (option == 2) {
                admin.getPermissions().remove(Permission.READ);
                System.out.println("Permission successfully removed");
            }
            if (option == 3) {
                admin.getPermissions().remove(Permission.DELETE);
                System.out.println("Permission successfully removed");
            }
        }
    }

    public void grantPermissions() {
        AdministratorRepository.printAdmins();
        int index = AskData.inputInteger("Index");
        Administrator admin = (Administrator) ClientRepository.getUserByIndex(index);
        if (admin.getPermissions().size() == 3) {
            System.out.println("None to do here");
        } else {
            if (!admin.getPermissions().contains(Permission.WRITE)) {
                System.out.println("1 - grant write permission");
            }
            if (!admin.getPermissions().contains(Permission.READ)) {
                System.out.println("2 - grant read permission");
            }
            if (!admin.getPermissions().contains(Permission.DELETE)) {
                System.out.println("3 - grant delete permission");
            }
            int option = AskData.inputInteger("Option");
            if (option == 1) {
                admin.getPermissions().add(Permission.WRITE);
                System.out.println("Permission successfully granted");
            }
            if (option == 2) {
                admin.getPermissions().add(Permission.READ);
                System.out.println("Permission successfully granted");
            }
            if (option == 3) {
                admin.getPermissions().add(Permission.DELETE);
                System.out.println("Permission successfully granted");
            }
        }
    }

    @Override
    public void execute(ArrayList<Permission> permissions) {
        AskData.bufferCleaner();
        Menu subMenu = new Menu();
        Controller createAdmin = (permission) -> createAdmin();
        Controller printAdmins = (permission) -> printAdmins();
        Controller removeAdmin = (permission) -> deleteAdmin();
        Controller modifyAdmin = (permission) -> modifyAdmin();
        Controller removePermissios = (permission) -> removePermissions();
        Controller grantPermissions = (permission) -> grantPermissions();

        if (permissions.isEmpty()) {
            System.out.println("None to do here");
        } else {
            if (permissions.contains(Permission.WRITE)) {
                subMenu.addMenuItem(1, new MenuItem("Create admin", createAdmin));
                subMenu.addMenuItem(4, new MenuItem("Modify admin", modifyAdmin));
                subMenu.addMenuItem(5, new MenuItem("Grant permissions", grantPermissions));
                subMenu.addMenuItem(6, new MenuItem("Remove permissions", removePermissios));
            }
            if (permissions.contains(Permission.READ)) {
                subMenu.addMenuItem(2, new MenuItem("Print admins", printAdmins));
            }
            if (permissions.contains(Permission.DELETE)) {
                subMenu.addMenuItem(3, new MenuItem("remove admin", removeAdmin));
            }
            subMenu.display("Admin Menu", permissions);
            AskData.bufferCleaner();
        }
    }

}
