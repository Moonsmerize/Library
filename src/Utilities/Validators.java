package Utilities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Validators {

    public static IntegerValidator dayValidator() {
        IntegerValidator dayValidator = (value) -> value > 0 && value < 32;
        return dayValidator;
    }

    public static IntegerValidator monthValidator() {
        IntegerValidator monthValidator = (value) -> value > 0 && value < 13;
        return monthValidator;
    }

    public static IntegerValidator yearValidator() {
        IntegerValidator yearValidator = (value) -> value > 0;
        return yearValidator;
    }

    public static StringValidator passwordValidator() {
        StringValidator passwordValidator = (string) -> {
            boolean hasLower = false, hasUpper = false,
                    hasDigit = false, specialChar = false;
            Set<Character> set = new HashSet<Character>(
                    Arrays.asList('!', '@', '#', '$', '%', '^', '&',
                            '*', '(', ')', '-', '+'));
            for (char i : string.toCharArray()) {
                if (Character.isLowerCase(i))
                    hasLower = true;
                if (Character.isUpperCase(i))
                    hasUpper = true;
                if (Character.isDigit(i))
                    hasDigit = true;
                if (set.contains(i))
                    specialChar = true;

            }
            if (hasDigit && hasLower && hasUpper && specialChar && string.length() >= 10) {
                return true;
            } else {
                return false;
            }
        };
        return passwordValidator;
    }

    public static StringValidator isbnValidator() {
        StringValidator isbnValidator = (value) -> value.length() == 13 && value.matches("[0-9.]+");
        return isbnValidator;
    }

}
