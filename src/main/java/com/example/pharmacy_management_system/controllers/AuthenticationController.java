package com.example.pharmacy_management_system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;

public class AuthenticationController {

    @FXML
    private SplitPane splitPane;

    @FXML
    public void initialize() {
        // Disable resizing the split pane divider
        splitPane.getDividers().get(0).positionProperty().addListener((obs, oldVal, newVal) -> {
            splitPane.setDividerPositions(0.5);  // Lock the divider at 50% position
        });
    }

    @FXML
    private void handleLoginAction() {
        // Your login logic here
    }
}

