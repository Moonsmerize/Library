package Utilities;

import java.util.ArrayList;

import Enums.Permission;
import Interfaces.Controller;
import Main.Main;

public class Logout implements Controller {

    @Override
    public void execute(ArrayList<Permission> permissions) {
        logOut();
    }

    public void logOut() {
        Main.logoutValidator = 0;
    }

}
