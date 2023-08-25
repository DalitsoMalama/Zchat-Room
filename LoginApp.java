import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginApp extends JFrame {
    protected JButton registerButton;
    protected JButton loginButton;
    protected JTextField usernameField;
    protected JPasswordField passwordField;

    protected UserAuthenticator userAuthenticator;

    // Constructor with UserAuthenticator parameter
    public LoginApp(UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator; // Set the userAuthenticator

        setTitle("ZChat Room Login/Signin");
        setSize(360, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set frame's content pane with a JLabel containing the background image
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("background.jpg"));
        JLabel backgroundLabel = new JLabel(backgroundImage) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(360, 540);
            }
        };
        setContentPane(backgroundLabel);
        setLayout(new GridBagLayout());

        JPanel authenticationPanel = new JPanel(new GridBagLayout());
        authenticationPanel.setOpaque(false);
        authenticationPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

JLabel title = new JLabel("ZChat Room");
title.setForeground(Color.DARK_GRAY); // Set text color to green
title.setFont(title.getFont().deriveFont(Font.BOLD, 24)); // Increase font size and make it bold
title.setHorizontalAlignment(SwingConstants.CENTER); // Center text horizontally

gbc.gridx = 0;
gbc.gridy = 0;
gbc.gridwidth = 2; // Span two columns
gbc.insets = new Insets(0, 0, 10, 0);
authenticationPanel.add(title, gbc);

int row = 1; // Start from the next row

JLabel usernameLabel = new JLabel("Username:");
gbc.gridx = 0;
gbc.gridy = row;
gbc.gridwidth = 1; // Reset gridwidth
gbc.insets = new Insets(0, 0, 10, 0);
authenticationPanel.add(usernameLabel, gbc);

usernameField = new JTextField();

gbc.gridx = 1;
gbc.gridy = row;
gbc.insets = new Insets(0, 10, 10, 0);
authenticationPanel.add(usernameField, gbc);

row++; // Move to the next row

JLabel passwordLabel = new JLabel("Password:");
gbc.gridx = 0;
gbc.gridy = row;
gbc.insets = new Insets(0, 0, 10, 0);
authenticationPanel.add(passwordLabel, gbc);

passwordField = new JPasswordField();
gbc.gridx = 1;
gbc.gridy = row;
gbc.insets = new Insets(0, 10, 10, 0);
authenticationPanel.add(passwordField, gbc);

row++; // Move to the next row

registerButton = new JButton("Register");
registerButton.setPreferredSize(new Dimension(100, 30));
gbc.gridx = 0;
gbc.gridy = row;
gbc.insets = new Insets(0, 0, 0, 10);
authenticationPanel.add(registerButton, gbc);

loginButton = new JButton("Login");
loginButton.setPreferredSize(new Dimension(100, 30));
gbc.gridx = 1;
gbc.gridy = row;
gbc.insets = new Insets(0, 10, 0, 0);
authenticationPanel.add(loginButton, gbc);

        add(authenticationPanel);

        setVisible(true);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    protected void register() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        boolean registrationSuccessful = userAuthenticator.registerUser(username, password);

        if (registrationSuccessful) {
            JOptionPane.showMessageDialog(this, "Registered successfully");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. Please try again.");
        }
    }

    protected void login() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        boolean loginSuccessful = userAuthenticator.loginUser(username, password);

        if (loginSuccessful) {
            JOptionPane.showMessageDialog(this, "Login successful");
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }

    protected void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
    }

    protected boolean isLoginSuccessful() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        return userAuthenticator.loginUser(username, password);
    }
}
