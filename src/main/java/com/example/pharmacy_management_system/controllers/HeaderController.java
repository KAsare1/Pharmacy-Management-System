package com.example.pharmacy_management_system.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HeaderController {

    public Button ReportsButton;
    public ProgressIndicator loadingIndicator;
    @FXML
    private Button dashboardButton;

    @FXML
    private Button salesButton;

    @FXML
    private Button inventoryButton;


    @FXML
    private Button addDrugButton;


    @FXML
    private Button notificationsButton;

    @FXML
    private Button settingsButton;

    @FXML
    protected void initialize() {
        dashboardButton.setOnAction(e -> switchToScene("Dashboard.fxml"));
        salesButton.setOnAction(e -> switchToScene("Sales.fxml"));
        inventoryButton.setOnAction(e -> switchToScene("Inventory.fxml"));
        addDrugButton.setOnAction(e -> switchToScene("AddDrug.fxml"));
        notificationsButton.setOnAction(e -> switchToScene("Notifications.fxml"));
        settingsButton.setOnAction(e -> switchToScene("Settings.fxml"));
    }

    public void switchToScene(String fxmlFile) {
        // Show the loading indicator before the task
        loadingIndicator.setVisible(true);

        // Run the loading in a background thread
        new Thread(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pharmacy_management_system/" + fxmlFile));
                Parent page = loader.load();

                // Update UI on the JavaFX Application Thread
                Platform.runLater(() -> {
                    StackPane root = (StackPane) dashboardButton.getScene().getRoot();
                    root.getChildren().clear();
                    root.getChildren().add(page);

                    // Hide the loading indicator after the scene is loaded
                    loadingIndicator.setVisible(false);
                });
            } catch (IOException e) {
                e.printStackTrace();
                // Hide the loading indicator if there's an error
                Platform.runLater(() -> loadingIndicator.setVisible(false));
            }
        }).start();
    }
}
