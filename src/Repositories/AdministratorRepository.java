package Repositories;

import java.util.ArrayList;
import java.util.Date;

import Enums.Permission;
import Utilities.DateFormatter;
import Entities.*;

public class AdministratorRepository {

    private static ArrayList<Administrator> administrators = new ArrayList<>();

    public static ArrayList<Administrator> getAdministrators() {
        return administrators;
    }

    public static void setAdministrators(ArrayList<Administrator> administrators) {
        AdministratorRepository.administrators = administrators;
    }

    public static void createAdmin(Administrator administrator) {
        if (superAdminExist()) {
            System.out.println("A super admin already exist");
            administrator.setSuperAdmin(false);
            ClientRepository.getUsers().add(administrator);
        } else {
            ClientRepository.getUsers().add(administrator);
        }
    }

    public static void modifyAdmin(int index, String name, String lastname, Date Birthday) {
        administrators.get(index).getProfile().setName(name);
        administrators.get(index).getProfile().setLastName(lastname);
        administrators.get(index).getProfile().setBirthdate(Birthday);
    }

    public static void deleteAdminByIndex(int index) {
        administrators.remove(index);
        System.out.println("\nClient successfully removed");
    }

    public static Administrator getAdminByIndex(int index) {
        return administrators.get(index);
    }

    public static boolean superAdminExist() {
        for (User user : ClientRepository.getUsers()) {
            if (user instanceof Administrator) {
                Administrator administrator = (Administrator) user;
                if (administrator.isSuperAdmin()) {
                    return true;
                } else {

                }
            }

        }
        return false;
    }

    public static int getAdministratorIndex(User user) {
        return administrators.indexOf(user);
    }

    public static void printAdmins() {
        int index = 0;
        System.out.printf(
                "---------------------------------------------------------------------------------------------------\n");
        System.out.printf(
                "|                                                Admins                                           |\n");
        System.out.printf(
                "---------------------------------------------------------------------------------------------------\n");
        System.out.printf("| %18s | %20s | %13s | %13s | %13s | %3s |\n", "Name", "Last name", "Birthday",
                "Permissions",
                "IssuperAdmin?", "i");
        System.out.printf(
                "---------------------------------------------------------------------------------------------------\n");
        for (User user : ClientRepository.getUsers()) {
            if (user instanceof Administrator) {
                Administrator administrator = (Administrator) user;
                System.out.printf("| %18s | %20s | %13s | %13s | %13s | %3s |\n", administrator.getProfile().getName(),
                        administrator.getProfile().getLastName(),
                        DateFormatter.Formatt(administrator.getProfile().getBirthdate()), "",
                        administrator.isSuperAdmin(), String.valueOf(index));
                if (administrator.getPermissions().contains(Permission.WRITE)) {
                    System.out.printf("| %18s | %20s | %13s | %13s | %13s | %3s |\n", "", "", "",
                            "Write", "", "");
                }
                if (administrator.getPermissions().contains(Permission.READ)) {
                    System.out.printf("| %18s | %20s | %13s | %13s | %13s | %3s |\n", "", "", "",
                            "Read", "", "");
                }
                if (administrator.getPermissions().contains(Permission.DELETE)) {
                    System.out.printf("| %18s | %20s | %13s | %13s | %13s | %3s |\n", "", "", "",
                            "Delete", "", "");
                }
                index++;
                System.out.printf(
                        "---------------------------------------------------------------------------------------------------\n");
            } else {
                index++;
            }

        }

    }

}
