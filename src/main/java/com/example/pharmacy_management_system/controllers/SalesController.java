package com.example.pharmacy_management_system.controllers;

import com.example.pharmacy_management_system.DBConnection;
import com.example.pharmacy_management_system.models.Sales;
import com.example.pharmacy_management_system.models.Drug;
import com.example.pharmacy_management_system.models.Supplier;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class SalesController {

    @FXML
    public TextField searchTextField;
    @FXML
    public Button searchButton;
    @FXML
    public Button generateReportButton;
    @FXML
    private TableColumn<Sales, Boolean> selectColumn;
    @FXML
    private TableView<Sales> purchaseHistoryTable;
    @FXML
    private TableColumn<Sales, String> drugSoldColumn;
    @FXML
    private TableColumn<Sales, Integer> quantityColumn;
    @FXML
    private TableColumn<Sales, Double> amountColumn;
    @FXML
    private TableColumn<Sales, String> buyerColumn;
    @FXML
    private TableColumn<Sales, Date> dateColumn;
    @FXML
    private TableColumn<Sales, Time> timeColumn;

    private ObservableList<Sales> saleList;

    @FXML
    public void initialize() {

        selectColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        selectColumn.setCellFactory(column -> new CheckBoxTableCell<>());

        selectColumn.setEditable(true);
        purchaseHistoryTable.setEditable(true);

        // Initialize columns
        drugSoldColumn.setCellValueFactory(cellData -> {
            Drug drug = cellData.getValue().getDrugSold(); // Get the Drug object
            return new SimpleStringProperty(drug.getDrugName()); // Return the drugName as a StringProperty
        });
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        buyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyer"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        loadSalesData();
        generateReportButton.setOnAction(event -> generateReports());

        // Search button event handler
        searchButton.setOnAction(event -> searchSale());

        // Load data

    }

    private void searchSale() {
        String searchText = searchTextField.getText().toLowerCase();
        ObservableList<Sales> filteredList = FXCollections.observableArrayList();
        for (Sales sales : saleList) {
            if (sales.getBuyer().toLowerCase().contains(searchText) ||
                    sales.getDrugSold().getDrugName().toLowerCase().contains(searchText)) {
                filteredList.add(sales);
            }
        }
        purchaseHistoryTable.setItems(filteredList);
    }


    private void generateReports() {
    }


    private void loadSalesData() {
        saleList = FXCollections.observableArrayList();

        // SQL query to retrieve sales data
        String query = "SELECT s.salesId, s.quantity, s.amount, s.buyer, s.date, s.time, " +
                "d.drugName, d.pricePerUnit, d.quantity AS drugQuantity, d.expirationDate, " +
                "d.supplierId " +  // Include the supplierId in the query
                "FROM sales s " +
                "JOIN drug d ON s.drug_sold = d.drugCode";

        try (Connection connection = DBConnection.getConnect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                // Create Drug instance
                Supplier supplier = new Supplier(
                        resultSet.getInt("supplierId"), // Adjust this if needed
                        "Supplier Name", // Replace with actual data or fetch from another source
                        "Location", // Replace with actual data or fetch from another source
                        "Contact" // Replace with actual data or fetch from another source
                );

                Drug drug = new Drug(
                        resultSet.getString("drugName"),
                        supplier,
                        resultSet.getDouble("pricePerUnit"),
                        resultSet.getInt("drugQuantity"),
                        resultSet.getDate("expirationDate") != null ? resultSet.getDate("expirationDate").toLocalDate() : null
                );

                // Create Sales instance
                Sales sale = new Sales(
                        resultSet.getInt("salesId"),
                        drug,
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("buyer"),
                        resultSet.getDate("date"),
                        resultSet.getTime("time")
                );

                // Add to the list
                saleList.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }

        // Set the items in the TableView
        purchaseHistoryTable.setItems(saleList);
    }
}
