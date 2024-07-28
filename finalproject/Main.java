package twentyfour.spring.oop.group2.finalproject.m23w7314;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false;
        while (!quit) {
            try {
                System.out.println("Welcome to the Quiz Application");
                System.out.println("1. Login");
                System.out.println("2. Create New Account");
                System.out.println("3. Forgot Password");
                System.out.println("4. Exit");

                System.out.print("Please select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        createNewAccount();
                        break;
                    case 3:
                        forgotPassword();
                        break;
                    case 4:
                        quit = true;
                        System.out.println("Exiting the application.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select again.");

                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer choice.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.close(); // Close the scanner when done
    }

    private static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (UserDatabase.authenticateUser(username, password)) {
            System.out.println("Login successful!");
            displayMenu.displayMenu(username, scanner); // Pass the username to displayMenu
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void createNewAccount() {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();

        // Password input with hidden characters
        String password = enterPassword();

        // Email validation
        String email = "";
        boolean validEmail = false;
        while (!validEmail) {
            System.out.print("Enter valid email address: ");
            email = scanner.nextLine();
            if (isValidEmail(email)) {
                validEmail = true;
            } else {
                System.out.println("Invalid email format. Please try again.");
            }
        }

        boolean created = UserDatabase.createUser(username, password, email);
        if (created) {
            System.out.println("Account created successfully!");
        }
    }

    private static String enterPassword() {
        String password = "";
        System.out.print("Enter password: ");
        password = scanner.nextLine();
        return password;
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static void forgotPassword() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();

        String verificationCode = UserDatabase.generateVerificationCode(username, email);
        if (verificationCode != null) {
            System.out.println("Verification code sent to your email. Please check and enter it below.");

            System.out.print("Enter verification code: ");
            String enteredCode = scanner.nextLine();

            if (UserDatabase.verifyVerificationCode(username, enteredCode)) {
                String newPassword = enterPassword();
                UserDatabase.updatePassword(username, newPassword);
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("Invalid verification code. Password reset failed.");
            }
        } else {
            System.out.println("User not found or email does not match. Password reset failed.");
        }
    }
}
