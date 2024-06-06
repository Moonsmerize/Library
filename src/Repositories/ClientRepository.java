package Repositories;

import java.util.ArrayList;
import java.util.Date;

import Controllers.ClientController;
import Entities.Book;
import Entities.Client;
import Entities.User;
import Utilities.DateFormatter;

@SuppressWarnings("deprecation")
public class ClientRepository {

        private static ArrayList<User> users = new ArrayList<>();

        public static void createClient(Client client) {
                users.add(client);
        }

        public static void modifyClient(int index, String name, String lastname, Date Birthday) {
                users.get(index).getProfile().setName(name);
                users.get(index).getProfile().setLastName(lastname);
                users.get(index).getProfile().setBirthdate(Birthday);
        }

        public static void deleteUserByIndex(int index) {
                users.remove(index);
        }

        public static void deleteClientByIndex(int index) {
                Client client = (Client) users.get(index);
                if (client.getBorrowedBooks() != null) {
                        users.remove(index);
                        System.out.println("\nClient successfully removed");
                } else {
                        System.out.println("\nSomething went wrong!!!");
                }
        }

        public static User getUserByIndex(int index) {
                return users.get(index);
        }

        public static void printAllClients() {
                int index = 0;
                System.out.printf(
                                "-------------------------------------------------------------------\n");
                System.out.printf(
                                "|                              Clients                            |\n");
                System.out.printf(
                                "-------------------------------------------------------------------\n");
                System.out.printf("| %18s | %20s | %13s | %3s |\n", "Name", "Last name", "Birthday", "i");
                System.out.printf(
                                "-------------------------------------------------------------------\n");
                for (User user : users) {
                        if (user instanceof Client) {
                                System.out.printf("| %18s | %20s | %13s | %3s |\n", user.getProfile().getName(),
                                                user.getProfile().getLastName(),
                                                DateFormatter.Formatt(user.getProfile().getBirthdate()),
                                                String.valueOf(index));
                                index++;
                        } else {
                                index++;
                        }

                }
                System.out.printf(
                                "-------------------------------------------------------------------\n\n");
        }

        public static Client getClientByIndex(int index) {
                Client client = (Client) users.get(index);
                return client;
        }

        public static void printClientBorrowedBooks() {

                ClientController clientController = new ClientController();
                Client client = clientController.getClientByIndex();
                if (!client.getBorrowedBooks().isEmpty()) {
                        System.out.printf(
                                        "---------------------------------------------------------------------------------------------------\n");
                        System.out.printf(
                                        "|                             Client "
                                                        + client.getProfile().getName()
                                                        + " " + client.getProfile().getLastName() + " borrowed books"
                                                        + "                             |\n");
                        System.out.printf(
                                        "---------------------------------------------------------------------------------------------------\n");
                        System.out.printf("| %18s | %20s | %20s | %15s | %10s |\n", "ISBN", "TITLE", "AUTHOR",
                                        "PUBLISH DATE",
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
                } else {
                        System.out.println("None to show");
                }
        }

        public static Client getClientByNameAndLastname(String name, String lastname) {
                for (User client1 : users) {
                        Client client = (Client) client1;
                        if (client.getProfile().getName().equals(name)
                                        && client.getProfile().getLastName().equals(lastname)) {

                                return client;
                        }
                }
                return null;
        }

        public static void printClientsWithBooks() {
                int index = 0;
                System.out.printf(
                                "-------------------------------------------------------------------\n");
                System.out.printf(
                                "|                          Clients with books                     |\n");
                System.out.printf(
                                "-------------------------------------------------------------------\n");
                System.out.printf("| %18s | %20s | %13s | %3s |\n", "Name", "Last name", "Birthday", "i");
                System.out.printf(
                                "-------------------------------------------------------------------\n");
                for (User user : users) {
                        if (user instanceof Client) {
                                Client client = (Client) user;
                                if (client.getBorrowedBooks().size() > 0) {
                                        System.out.printf("| %18s | %20s | %13s | %3s |\n",
                                                        client.getProfile().getName(),
                                                        client.getProfile().getLastName(),
                                                        DateFormatter.Formatt(client.getProfile().getBirthdate()),
                                                        index);
                                        index++;
                                } else {
                                        index++;
                                }

                        }
                }
                System.out.printf(
                                "-------------------------------------------------------------------\n");
        }

        public static void printAvailableClients() {
                int index = 0;
                System.out.printf(
                                "-------------------------------------------------------------------\n");
                System.out.printf(
                                "|                           Available Clients                     |\n");
                System.out.printf(
                                "-------------------------------------------------------------------\n");
                System.out.printf("| %18s | %20s | %13s | %3s |\n", "Name", "Last name", "Birthday", "i");
                System.out.printf(
                                "-------------------------------------------------------------------\n");

                for (User user : users) {
                        if (user instanceof Client) {
                                Client client = (Client) user;
                                if (client.getBorrowedBooks().size() < 3) {
                                        System.out.printf("| %18s | %20s | %13s | %3s |\n",
                                                        client.getProfile().getName(),
                                                        client.getProfile().getLastName(),
                                                        DateFormatter.Formatt(client.getProfile().getBirthdate()),
                                                        index);
                                }
                                index++;
                        } else {
                                index++;
                        }

                }
                System.out.printf(
                                "-------------------------------------------------------------------\n\n");
        }

        public static boolean checkClientExist(String name, String lastname) {
                for (User user : users) {
                        if (user.getProfile().getName().equals(name)
                                        && user.getProfile().getLastName().equals(lastname)) {
                                return true;
                        }
                }
                return false;
        }

        public static ArrayList<User> getUsers() {
                return users;
        }

}
