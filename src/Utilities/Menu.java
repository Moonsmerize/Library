package Utilities;

import java.util.ArrayList;
import java.util.HashMap;

import Entities.MenuItem;
import Enums.Permission;

public class Menu {
    private HashMap<Integer, MenuItem> menuItems;

    public Menu() {
        menuItems = new HashMap<>();
    }

    public void display(String prompt, ArrayList<Permission> permissions) {
        System.out.println(prompt);
        for (Integer key : menuItems.keySet()) {
            MenuItem menuItem = menuItems.get(key);
            System.out.println(key + " - " + menuItem.getText());
        }

        int option = AskData.inputInteger("Option");
        MenuItem menuItem = menuItems.get(option);
        menuItem.getController().execute(permissions);

    }

    public void addMenuItem(int key, MenuItem menuItem) {
        menuItems.put(key, menuItem);
    }

    public HashMap<Integer, MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(HashMap<Integer, MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

}