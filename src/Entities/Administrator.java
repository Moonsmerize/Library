package Entities;

import java.util.ArrayList;

import Enums.Permission;
import Repositories.AdministratorRepository;

public class Administrator extends User {

    private ArrayList<Permission> permissions;
    private boolean isSuperAdmin;

    public Administrator(Profile proflie, String usermane, String password, boolean isSuperAdmin) {
        super(proflie, usermane, password);
        this.permissions = new ArrayList<Permission>();
        permissions.add(Permission.DELETE);
        permissions.add(Permission.READ);
        permissions.add(Permission.WRITE);
        if (isSuperAdmin && !AdministratorRepository.superAdminExist()) {
            this.isSuperAdmin = true;
        } else {
            System.out.println("A super admin already exist");
            this.isSuperAdmin = false;
        }
    }

    public Administrator(Profile profile, String usermane, String password, ArrayList<Permission> permissions,
            boolean isSuperAdmin) {
        super(profile, usermane, password);
        this.permissions = permissions;
        if (isSuperAdmin && !AdministratorRepository.superAdminExist()) {
            this.isSuperAdmin = true;
        } else {
            System.out.println("A super admin already exist");
            this.isSuperAdmin = false;
        }
    }

    public ArrayList<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<Permission> permissions) {
        this.permissions = permissions;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

}
