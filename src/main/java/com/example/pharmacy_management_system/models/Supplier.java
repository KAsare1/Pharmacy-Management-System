package com.example.pharmacy_management_system.models;

import javafx.beans.property.*;

public class Supplier {
    private final IntegerProperty supplierId;
    private final StringProperty supplierName;
    private final StringProperty location;
    private final StringProperty contactNumber;

    public Supplier(int supplierId, String supplierName, String location, String contactNumber) {
        this.supplierId = new SimpleIntegerProperty(supplierId);
        this.supplierName = new SimpleStringProperty(supplierName);
        this.location = new SimpleStringProperty(location);
        this.contactNumber = new SimpleStringProperty(contactNumber);
    }

    public int getSupplierId() {
        return supplierId.get();
    }

    public void setSupplierId(int supplierId) {
        this.supplierId.set(supplierId);
    }

    public IntegerProperty supplierIdProperty() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName.get();
    }

    public void setSupplierName(String supplierName) {
        this.supplierName.set(supplierName);
    }

    public StringProperty supplierNameProperty() {
        return supplierName;
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public StringProperty locationProperty() {
        return location;
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber.set(contactNumber);
    }

    public StringProperty contactNumberProperty() {
        return contactNumber;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + getSupplierId() +
                ", supplierName='" + getSupplierName() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", contactNumber='" + getContactNumber() + '\'' +
                '}';
    }
}
