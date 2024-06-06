package Entities;

import java.util.ArrayList;

import Enums.Permission;
import Interfaces.Controller;

public class MenuItem {

    private String text;
    private Controller controller;
    private ArrayList<Permission> permission;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public MenuItem(String text, Controller controller) {
        this.text = text;
        this.controller = controller;
    }

    public MenuItem(String text, Controller controller, ArrayList<Permission> permissions) {
        this.text = text;
        this.controller = controller;
        this.permission = permissions;
    }

    public ArrayList<Permission> getPermission() {
        return permission;
    }

    public void setPermission(ArrayList<Permission> permission) {
        this.permission = permission;
    }

}