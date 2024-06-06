package Utilities;

import java.util.Scanner;

public class AskData {

    private static Scanner input = new Scanner(System.in);

    public static String inputString(String prompt) {
        System.out.print(prompt + ": ");
        String string = input.nextLine();
        return string;
    }

    public static String inputString(String prompt, StringValidator validator) {
        boolean isValid = false;
        do {
            System.out.print(prompt + ": ");
            String string = input.nextLine();
            isValid = validator.stringValidator(string);
            if (isValid)
                return string;
            else
                System.out.println("Try again");
        } while (!isValid);
        return null;
    }

    public static int inputInteger(String prompt) {
        System.out.print(prompt + ": ");
        int integer = input.nextInt();
        return integer;
    }

    public static int inputInteger(String prompt, IntegerValidator validator) {
        boolean isValid = false;
        do {
            System.out.print(prompt + ": ");
            int integer = input.nextInt();
            isValid = validator.integerValidator(integer);
            if (isValid) {
                return integer;
            } else
                System.out.println("Try again");
        } while (!isValid);
        return -1;
    }

    public static boolean inputBoolean(String prompt) {
        System.out.print(prompt + ": ");
        boolean Boolean = input.nextBoolean();
        return Boolean;
    }

    public static void bufferCleaner() {
        input.nextLine();
    }

}
