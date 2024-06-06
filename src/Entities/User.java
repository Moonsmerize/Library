package Entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class User {

    private Profile profile;
    private String usermane;
    private String password;

    public User(Profile profile, String usermane, String password) {
        this.profile = profile;
        this.usermane = usermane;
        this.password = sha256(password);
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUsermane() {
        return usermane;
    }

    public void setUsermane(String usermane) {
        this.usermane = usermane;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = sha256(password);
    }

    public static String checkPassword(String password) {
        return sha256(password);
    }

    public static String sha256(String password) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] digest = sha.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }

    }

}
