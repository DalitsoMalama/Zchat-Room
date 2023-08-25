  import javax.swing.*;

import javafx.event.ActionEvent;

public class Maintest {

 public static void main(String[] args) {
        UserAuthenticator userAuthenticator = new UserAuthenticator();
        LoginApp loginApp = new LoginApp(userAuthenticator);

        while (true) {
            boolean loginSuccessful = loginApp.isLoginSuccessful();
            if (loginSuccessful) {
                // Perform something if login is successful
                System.out.println("Login successful. Starting something...");
                break; // Exit the loop since login is successful
            } else {
                // If login is not successful, loop back to the login screen
                System.out.println("Invalid login. Please try again.");
                loginApp.clearFields();
            }
        }
    }
}

    

