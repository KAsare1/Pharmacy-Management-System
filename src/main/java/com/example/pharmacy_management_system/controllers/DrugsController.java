package com.example.pharmacy_management_system.controllers;

import com.example.pharmacy_management_system.DBConnection;
import com.example.pharmacy_management_system.models.Drug;
import com.example.pharmacy_management_system.models.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class DrugsController {

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Drug> drugTable;

    @FXML
    private TableColumn<Drug, Boolean> selectColumn;

    @FXML
    private TableColumn<Drug, String> drugNameColumn;

    @FXML
    private TableColumn<Drug, Integer> quantityColumn;

    @FXML
    private TableColumn<Drug, Double> priceColumn;

    @FXML
    private TableColumn<Drug, Supplier> supplierColumn; // Changed to Supplier

    @FXML
    private TableColumn<Drug, LocalDate> expiryDateColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button removeButton;

    @FXML
    private TextField searchTextField;

    private ObservableList<Drug> drugList;

    @FXML
    private void initialize() {
        // Initialize columns
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        selectColumn.setCellFactory(column -> new CheckBoxTableCell<>());

        drugTable.setEditable(true);
        selectColumn.setEditable(true);

        drugNameColumn.setCellValueFactory(cellData -> cellData.getValue().drugNameProperty());

        // Update supplier column to use Supplier type and display name
        supplierColumn.setCellValueFactory(cellData -> cellData.getValue().supplierProperty());
        supplierColumn.setCellFactory(column -> new TableCell<Drug, Supplier>() {
            @Override
            protected void updateItem(Supplier supplier, boolean empty) {
                super.updateItem(supplier, empty);
                setText(empty ? null : supplier == null ? "N/A" : supplier.getSupplierName());
            }
        });

        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        expiryDateColumn.setCellValueFactory(cellData -> cellData.getValue().expiryDateProperty());

        // Load data from database
        loadDrugData();

        // Add button event handlers
        addButton.setOnAction(event -> openAddDrugDialog());
        removeButton.setOnAction(event -> removeSelectedDrugs());

        // Search button event handler
        searchButton.setOnAction(event -> searchDrug());
    }

    private void loadDrugData() {
        drugList = FXCollections.observableArrayList();
        String query = "SELECT d.drugName, d.pricePerUnit, d.quantity, d.expirationDate, s.supplierId, s.supplierName, s.location, s.contactNumber " +
                "FROM drug d JOIN supplier s ON d.supplierId = s.supplierId"; // Updated query to join supplier

        try (Connection conn = DBConnection.getConnect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Supplier supplier = new Supplier(
                        rs.getInt("supplierId"),
                        rs.getString("supplierName"),
                        rs.getString("location"),
                        rs.getString("contactNumber")
                );

                Drug drug = new Drug(
                        rs.getString("drugName"),
                        supplier,
                        rs.getDouble("pricePerUnit"),
                        rs.getInt("quantity"),
                        rs.getDate("expirationDate").toLocalDate()
                );
                drug.setSelected(false); // Set initial state
                drugList.add(drug);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading drug data: " + e.getMessage());
        }
        drugTable.setItems(drugList);
    }

    private void openAddDrugDialog() {
        try {
            // Load the AddDrug scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pharmacy_management_system/AddDrug.fxml"));
            Parent addDrugPage = loader.load();
            Scene addDrugScene = new Scene(addDrugPage);

            // Get the current stage
            Stage currentStage = (Stage) addButton.getScene().getWindow();

            // Switch to the new scene
            currentStage.setScene(addDrugScene);

            // Optionally restore the previous window size and state
            currentStage.show();
        } catch (IOException e) {
            System.out.println("Error opening Add Drug dialog");
        }
    }

    private void removeSelectedDrugs() {
        ObservableList<Drug> selectedDrugs = FXCollections.observableArrayList();
        for (Drug drug : drugList) {
            if (drug.isSelected()) {
                selectedDrugs.add(drug);
            }
        }
        if (!selectedDrugs.isEmpty()) {
            String query = "DELETE FROM drug WHERE drugName = ?";
            try (Connection conn = DBConnection.getConnect();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                for (Drug drug : selectedDrugs) {
                    pstmt.setString(1, drug.getDrugName());
                    pstmt.executeUpdate();
                }
                drugList.removeAll(selectedDrugs);
                System.out.println("Selected drugs removed successfully!");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error removing selected drugs: " + e.getMessage());
            }
        } else {
            System.out.println("No drugs selected");
        }
    }

    private void searchDrug() {
        String searchText = searchTextField.getText().toLowerCase();
        ObservableList<Drug> filteredList = FXCollections.observableArrayList();
        for (Drug drug : drugList) {
            if (drug.getDrugName().toLowerCase().contains(searchText) ||
                    drug.getSupplier().getSupplierName().toLowerCase().contains(searchText)) { // Adjusted for Supplier
                filteredList.add(drug);
            }
        }
        drugTable.setItems(filteredList);
    }
}
