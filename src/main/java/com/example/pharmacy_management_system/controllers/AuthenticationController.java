package com.example.pharmacy_management_system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthenticationController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button LoginButton;

    @FXML
    private Label errorLabel;

    @FXML
    private SplitPane splitPane;

    // Hardcoded credentials
    private final String validUsername = "admin";
    private final String validPassword = "password123";

    @FXML
    public void initialize() {
        LoginButton.setOnAction(e -> openDashboard());

        splitPane.getDividers().get(0).positionProperty().addListener((obs, oldVal, newVal) -> {
            splitPane.setDividerPositions(0.5);  // Lock the divider at 50% position
        });
    }

    private void openDashboard() {
            try {
                // Load the AddDrug scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pharmacy_management_system/Dashboard.fxml"));
                Parent addDrugPage = loader.load();
                Scene addDrugScene = new Scene(addDrugPage);

                // Get the current stage
                Stage currentStage = (Stage) LoginButton.getScene().getWindow();

                // Switch to the new scene
                currentStage.setScene(addDrugScene);

                // Optionally restore the previous window size and state
                currentStage.show();
            } catch (IOException e) {
                System.out.println("Error opening Add Drug dialog");
            }
    }

    @FXML
    private void handleLoginAction(ActionEvent event) {
        // Retrieve username and password from input fields
        String enteredUsername = usernameField.getText();
        String enteredPassword = passwordField.getText();

        // Check if the entered credentials match the hardcoded credentials
        if (enteredUsername.equals(validUsername) && enteredPassword.equals(validPassword)) {
            // Successful login logic here
            errorLabel.setText("Login successful!");
            errorLabel.setStyle("-fx-text-fill: green;");

            // You can navigate to the next scene or dashboard here
        } else {
            // Display error message
            errorLabel.setText("Invalid username or password.");
            errorLabel.setStyle("-fx-text-fill: red;");
        }
    }
}
