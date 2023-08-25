
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Backend class responsible for user authentication logic
class UserAuthenticator {
    // HashMap to store the user credentials
    protected HashMap<String, String> userCredentials;

    public UserAuthenticator() {
        userCredentials = new HashMap<>();
    }

    // Method to register a new user
    public boolean registerUser(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false; // Registration failed, empty username/password
        }

        if (userCredentials.containsKey(username)) {
            return false; // Registration failed, username already exists
        }

        // Hash the password
        String hashedPassword = hashPassword(password);

        // Store the hashed password in the credentials map
        userCredentials.put(username, hashedPassword);

        // Write the credentials to a file
        try {
            FileWriter writer = new FileWriter("users.txt", true);
            writer.write(username + ":" + hashedPassword + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true; // Registration successful
    }

    // Method to perform user login
    public boolean loginUser(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false; // Login failed, empty username/password
        }

        String hashedPassword = hashPassword(password);

        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(hashedPassword)) {
            return true; // Login successful
        } else {
            return false; // Login failed, invalid username or password
        }
    }

    // Method to hash the user password using SHA-256 algorithm
    protected String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
