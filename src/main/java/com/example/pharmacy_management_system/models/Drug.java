package com.example.pharmacy_management_system.models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Drug {
    private final StringProperty drugName;
    private final ObjectProperty<Supplier> supplier; // Changed from String to Supplier
    private final DoubleProperty price;
    private final IntegerProperty quantity;
    private final ObjectProperty<LocalDate> expiryDate;
    private final BooleanProperty selected;

    public Drug(String drugName, Supplier supplier, double price, int quantity, LocalDate expiryDate) {
        this.drugName = new SimpleStringProperty(drugName);
        this.supplier = new SimpleObjectProperty<>(supplier);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.expiryDate = new SimpleObjectProperty<>(expiryDate);
        this.selected = new SimpleBooleanProperty(false);
    }

    // Getters and setters for drugName
    public String getDrugName() {
        return drugName.get();
    }

    public void setDrugName(String drugName) {
        this.drugName.set(drugName);
    }

    public StringProperty drugNameProperty() {
        return drugName;
    }

    // Getters and setters for supplier
    public Supplier getSupplier() {
        return supplier.get();
    }

    public void setSupplier(Supplier supplier) {
        this.supplier.set(supplier);
    }

    public ObjectProperty<Supplier> supplierProperty() {
        return supplier;
    }

    // Getters and setters for price
    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    // Getters and setters for quantity
    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    // Getters and setters for expiryDate
    public LocalDate getExpiryDate() {
        return expiryDate.get();
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate.set(expiryDate);
    }

    public ObjectProperty<LocalDate> expiryDateProperty() {
        return expiryDate;
    }

    // Getters and setters for selected
    public boolean isSelected() {
        return selected.get();
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drugName='" + getDrugName() + '\'' +
                ", supplier=" + getSupplier() + // Updated to use Supplier's toString
                ", price=" + getPrice() +
                ", quantity=" + getQuantity() +
                ", expiryDate=" + getExpiryDate() +
                ", selected=" + isSelected() +
                '}';
    }

    public String getName() {
        return drugName.get();
    }
}
