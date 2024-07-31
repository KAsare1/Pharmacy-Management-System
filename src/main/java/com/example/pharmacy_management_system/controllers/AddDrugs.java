package com.example.pharmacy_management_system.controllers;

import com.example.pharmacy_management_system.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDrugs {

    @FXML
    private TextField drugNameField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField supplierField;

    @FXML
    private DatePicker expirationDatePicker;

    @FXML
    private Button addDrug;

    @FXML
    private void addDrug() {
        // Collect data from the fields
        String drugName = drugNameField.getText();
        String quantity = quantityField.getText();
        String price = priceField.getText();
        String supplier = supplierField.getText();
        Date expiryDate = Date.valueOf(expirationDatePicker.getValue());

        // SQL query to insert data
        String query = "INSERT INTO drug (drugName, supplier, pricePerUnit, quantity, expirationDate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set values to the query parameters
            pstmt.setString(1, drugName);
            pstmt.setString(4, quantity);
            pstmt.setString(3, price);
            pstmt.setString(2, supplier);
            pstmt.setDate(5, expiryDate);

            // Execute the query
            pstmt.executeUpdate();

            // Optionally, you can add some feedback to the user here
            clearInputFields();
            showAlert(Alert.AlertType.INFORMATION, "Drug Added", "Drug has been added successfully!");


        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
            System.err.println("Error adding drug: " + e.getMessage());
        }
    }

    private void clearInputFields() {
        drugNameField.clear();
        quantityField.clear();
        priceField.clear();
        supplierField.clear();
        expirationDatePicker.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
