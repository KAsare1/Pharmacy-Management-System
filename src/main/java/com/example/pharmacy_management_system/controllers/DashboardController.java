package com.example.pharmacy_management_system.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DashboardController {

    @FXML private Label totalDrugsLabel;
    @FXML private Label totalSalesLabel;
    @FXML private Label totalCustomersLabel;
    @FXML private Label totalOrdersLabel;

    @FXML private LineChart<String, Number> salesLineChart;
    @FXML private BarChart<String, Number> financialBarChart;
    @FXML private BarChart<String, Number> drugInventoryBarChart;
    @FXML private PieChart supplierPieChart;
    @FXML private BarChart<String, Number> expiringDrugsBarChart;
    @FXML private BarChart<String, Number> topSellingDrugsBarChart;

    @FXML
    public void initialize() {
        setupOverviewSection();
        setupSalesLineChart();
        setupFinancialBarChart();
        setupDrugInventoryBarChart();
        setupSupplierPieChart();
        setupExpiringDrugsBarChart();
        setupTopSellingDrugsBarChart();
    }

    private void setupOverviewSection() {
        totalDrugsLabel.setText("1217");
        totalSalesLabel.setText("$100,020.00");
        totalCustomersLabel.setText("1240");
        totalOrdersLabel.setText("1,245");
    }

    private void setupSalesLineChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("2024");
        series.getData().add(new XYChart.Data<>("Jan", 8000));
        series.getData().add(new XYChart.Data<>("Feb", 7500));
        series.getData().add(new XYChart.Data<>("Mar", 8500));
        series.getData().add(new XYChart.Data<>("Apr", 9000));
        series.getData().add(new XYChart.Data<>("May", 12000));
        series.getData().add(new XYChart.Data<>("Jun", 14000));

        salesLineChart.getData().add(series);
    }

    private void setupFinancialBarChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("2024");
        series.getData().add(new XYChart.Data<>("Jan", 5000));
        series.getData().add(new XYChart.Data<>("Feb", 4500));
        series.getData().add(new XYChart.Data<>("Mar", 5500));
        series.getData().add(new XYChart.Data<>("Apr", 6000));
        series.getData().add(new XYChart.Data<>("May", 8000));
        series.getData().add(new XYChart.Data<>("Jun", 10000));

        financialBarChart.getData().add(series);
    }

    private void setupDrugInventoryBarChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Inventory");
        series.getData().add(new XYChart.Data<>("Antibiotics", 2000));
        series.getData().add(new XYChart.Data<>("Painkillers", 3000));
        series.getData().add(new XYChart.Data<>("Vitamins", 1500));
        series.getData().add(new XYChart.Data<>("Vaccines", 1000));

        drugInventoryBarChart.getData().add(series);
    }

    private void setupSupplierPieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Eon Labs, Inc", 40),
                new PieChart.Data("Antigen Laboratory", 30),
                new PieChart.Data("Ernest Chemists", 20),
                new PieChart.Data("Zylera Pharmaceutical", 10)
        );

        supplierPieChart.setData(pieChartData);
    }

    private void setupExpiringDrugsBarChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Expiring Drugs");
        series.getData().add(new XYChart.Data<>("Antibiotics", 200));
        series.getData().add(new XYChart.Data<>("Painkillers", 150));
        series.getData().add(new XYChart.Data<>("Vitamins", 100));
        series.getData().add(new XYChart.Data<>("Vaccines", 50));

        expiringDrugsBarChart.getData().add(series);
    }

    private void setupTopSellingDrugsBarChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Top-Selling Drugs");
        series.getData().add(new XYChart.Data<>("Antibiotics", 400));
        series.getData().add(new XYChart.Data<>("Painkillers", 350));
        series.getData().add(new XYChart.Data<>("Vitamins", 300));
        series.getData().add(new XYChart.Data<>("Vaccines", 250));

        topSellingDrugsBarChart.getData().add(series);
    }
}