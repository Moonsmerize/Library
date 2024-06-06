package Utilities;

import Entities.User;
import Repositories.ClientRepository;

public class Login {

    public static User login(String username, String password) {
        for (User user : ClientRepository.getUsers()) {
            if (user.getPassword().equals(User.checkPassword(password)) && user.getUsermane().equals(username)) {
                return user;
            }
        }
        return null;
    }

}