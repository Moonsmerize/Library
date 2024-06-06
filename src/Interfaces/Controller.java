package Interfaces;

import java.util.ArrayList;

import Enums.Permission;

@FunctionalInterface
public interface Controller {
    void execute(ArrayList<Permission> permissions);
}
