package com.example.pharmacy_management_system.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HeaderController {

    public Button ReportsButton;
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

    private void switchToScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pharmacy_management_system/" + fxmlFile));
            Parent page = loader.load();
            Scene scene = new Scene(page);

            // Get the current stage
            Stage currentStage = (Stage) dashboardButton.getScene().getWindow();
            double currentWidth = currentStage.getWidth();
            double currentHeight = currentStage.getHeight();
            boolean isMaximized = currentStage.isMaximized();

            currentStage.setScene(scene);

            // Restore the window size
            currentStage.setWidth(currentWidth);
            currentStage.setHeight(currentHeight);
            currentStage.setMaximized(isMaximized);

            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Log the error to find out what went wrong
        }
    }
}
