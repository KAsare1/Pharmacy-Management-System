package com.example.pharmacy_management_system.controllers;

import com.example.pharmacy_management_system.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class AddDrugs {

    @FXML
    private TextField drugNameField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField priceField;

    @FXML
    private ComboBox<String> supplierComboBox; // Updated to ComboBox

    @FXML
    private DatePicker expirationDatePicker;

    @FXML
    private Button addDrugButton;

    @FXML
    public void initialize() {
        loadSuppliers();
    }

    private void loadSuppliers() {
        String query = "SELECT supplierName FROM supplier";
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String supplierName = rs.getString("supplierName");
                supplierComboBox.getItems().add(supplierName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "Error loading suppliers.");
        }
    }

    @FXML
    private void addDrug() {
        // Collect data from the fields
        String drugName = drugNameField.getText();
        String quantityStr = quantityField.getText();
        String priceStr = priceField.getText();
        String supplierName = supplierComboBox.getValue();
        Date expiryDate = Date.valueOf(expirationDatePicker.getValue());

        // Validate inputs
        if (drugName.isEmpty() || quantityStr.isEmpty() || priceStr.isEmpty() || supplierName == null || expiryDate == null) {
            showAlert(AlertType.ERROR, "Input Error", "Please fill in all fields.");
            return;
        }

        int quantity;
        double price;
        try {
            quantity = Integer.parseInt(quantityStr);
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Input Error", "Quantity and Price must be numbers.");
            return;
        }

        // SQL query to get supplierId by supplierName
        String supplierQuery = "SELECT supplierId FROM supplier WHERE supplierName = ?";
        int supplierId = -1;
        try (Connection conn = DBConnection.getConnect();
             PreparedStatement supplierStmt = conn.prepareStatement(supplierQuery)) {

            supplierStmt.setString(1, supplierName);
            ResultSet rs = supplierStmt.executeQuery();

            if (rs.next()) {
                supplierId = rs.getInt("supplierId");
            } else {
                showAlert(AlertType.ERROR, "Supplier Not Found", "Supplier with name " + supplierName + " not found.");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "Error fetching supplier ID.");
            return;
        }

        // SQL query to insert drug
        String query = "INSERT INTO drug (drugName, supplierId, pricePerUnit, quantity, expirationDate) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set values to the query parameters
            pstmt.setString(1, drugName);
            pstmt.setInt(2, supplierId);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, quantity);
            pstmt.setDate(5, expiryDate);

            // Execute the query
            pstmt.executeUpdate();
            showAlert(AlertType.INFORMATION, "Drug Added", "Drug has been added successfully!");

            // Clear input fields
            clearInputFields();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "Database Error", "Error adding drug.");
        }
    }

    private void clearInputFields() {
        drugNameField.clear();
        quantityField.clear();
        priceField.clear();
        supplierComboBox.getSelectionModel().clearSelection();
        expirationDatePicker.setValue(null);
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
