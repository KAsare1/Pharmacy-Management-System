package com.example.pharmacy_management_system.models;

public class Supplier {
    private int supplierId;
    private String supplierName;
    private String location;

    public Supplier(int supplierId, String supplierName, String location, String contactNumber) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.location = location;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public String getName() {
        return "";
    }

    public String getContactNumber() {
        return null;
    }

    public String toLowerCase() {
        return supplierName.toLowerCase();
    }
}