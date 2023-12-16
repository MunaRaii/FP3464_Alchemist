package utility;

import java.util.Scanner;

public class InputUtility {
    public static Scanner scanner = new Scanner(System.in);

    public static int getInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                scanner = new Scanner(System.in);
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception ex) {
                System.out.println("Invalid Input!!!");
            }
        }
    }

    public static double getDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                scanner = new Scanner(System.in);
                return scanner.nextDouble();
            } catch (Exception ex) {
                System.out.println("Invalid Input!!!");
            }
        }
    }

    public static String getString(String message) {
        while (true) {
            System.out.print(message);
            try {
                scanner = new Scanner(System.in);
                return scanner.nextLine();
            } catch (Exception ex) {
                System.out.println("Invalid Input!!!");
            }
        }
    }
}
