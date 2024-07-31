package com.example.pharmacy_management_system.controllers;

import com.example.pharmacy_management_system.DBConnection;
import com.example.pharmacy_management_system.models.Sales;
import com.example.pharmacy_management_system.models.Drug;
import com.example.pharmacy_management_system.models.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

public class SalesController {

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

    @FXML
    public void initialize() {
        // Initialize columns
        drugSoldColumn.setCellValueFactory(new PropertyValueFactory<>("drugSold"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        buyerColumn.setCellValueFactory(new PropertyValueFactory<>("buyer"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        // Load data
        loadSalesData();
    }


    private void loadSalesData() {
        ObservableList<Sales> salesList = FXCollections.observableArrayList();

        // SQL query to retrieve sales data
        String query = "SELECT s.salesId, s.quantity, s.amount, s.buyer, s.date, s.time, " +
                "d.drugName, d.pricePerUnit, d.quantity AS drugQuantity, d.expirationDate " +
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
                        resultSet.getInt("sales_id"),
                        drug,
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("buyer"),
                        resultSet.getDate("date"),
                        resultSet.getTime("time")
                );

                // Add to the list
                salesList.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception properly
        }

        // Set the items in the TableView
        purchaseHistoryTable.setItems(salesList);
    }
}
