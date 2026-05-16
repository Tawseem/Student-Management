package com.sms.controller;

import com.sms.main.MainApp;
import com.sms.service.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Label errorLabel;

    private AuthService authService;

    public void initialize() {
        authService = new AuthService();
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Username and Password cannot be empty!");
            return;
        }

        if (authService.login(username, password)) {
            errorLabel.setText("");
            // Navigate to Dashboard
            MainApp.showDashboard();
        } else {
            errorLabel.setText("Invalid credentials! Default is admin/admin");
        }
    }
}
