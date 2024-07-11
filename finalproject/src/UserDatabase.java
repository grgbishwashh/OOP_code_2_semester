package twentyfour.spring.oop.group2.finalproject.src;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class UserDatabase {
    private static final String FILE_PATH = "D:\\KCGI\\src\\twentyfour\\spring\\oop\\group2\\finalproject\\resources\\users.txt";
    private static Map<String, UserData> users = new HashMap<>();

    // Static initialization block to load users from file when the class is loaded
    static {
        loadUsersFromFile();
    }

    // Method to load users from file into the users map
    private static void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0];
                    String hashedPassword = parts[1];
                    String email = parts[2];
                    users.put(username, new UserData(hashedPassword, email));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
        }
    }

    // Method to save users from the users map to file
    private static void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Map.Entry<String, UserData> entry : users.entrySet()) {
                String username = entry.getKey();
                String hashedPassword = entry.getValue().getHashedPassword();
                String email = entry.getValue().getEmail();
                writer.write(username + "," + hashedPassword + "," + email);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

    // Method to create a new user and store in the users map and file
    public static boolean createUser(String username, String password, String email) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return false;
        }
        String hashedPassword = PasswordUtils.hashPassword(password);
        users.put(username, new UserData(hashedPassword, email));
        saveUsersToFile(); // Update the file after adding a new user
        System.out.println("User created successfully.");
        return true;
    }

    // Method to authenticate a user based on username and password
    public static boolean authenticateUser(String username, String password) {
        if (!users.containsKey(username)) {
            return false;
        }
        String hashedPassword = PasswordUtils.hashPassword(password);
        return users.get(username).getHashedPassword().equals(hashedPassword);
    }

    // Method to generate a verification code and send it via email (simulated here)
    public static String generateVerificationCode(String username, String email) {
        if (users.containsKey(username) && users.get(username).getEmail().equals(email)) {
            // Generate a random 6-digit verification code
            Random random = new Random();
            int verificationCode = 100000 + random.nextInt(900000);
            // For simplicity, we print the code here; in a real app, this would be sent via email
            System.out.println("Verification code generated: " + verificationCode);
            return String.valueOf(verificationCode);
        }
        return null;
    }

    // Method to verify the entered verification code
    public static boolean verifyVerificationCode(String username, String enteredCode) {
        // In a real implementation, this would compare the entered code with a stored one
        return true; // For simplicity, always return true in this example
    }

    // Method to update the password for a user
    public static void updatePassword(String username, String newPassword) {
        users.get(username).setHashedPassword(PasswordUtils.hashPassword(newPassword));
        saveUsersToFile();
    }

    // Utility method to get all users (for testing or future enhancements)
    public static Map<String, UserData> getAllUsers() {
        return users;
    }

    // Inner class to hold user data (hashed password and email)
    private static class UserData {
        private String hashedPassword;
        private String email;

        public UserData(String hashedPassword, String email) {
            this.hashedPassword = hashedPassword;
            this.email = email;
        }

        public String getHashedPassword() {
            return hashedPassword;
        }

        public void setHashedPassword(String hashedPassword) {
            this.hashedPassword = hashedPassword;
        }

        public String getEmail() {
            return email;
        }
    }
}
