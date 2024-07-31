package com.example.pharmacy_management_system.models;

import javafx.beans.property.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Sales {
    private final IntegerProperty salesId;
    private final ObjectProperty<Drug> drugSold;
    private final IntegerProperty quantity;
    private final DoubleProperty amount;
    private final StringProperty buyer;
    private final ObjectProperty<Date> date;
    private final ObjectProperty<Time> time;


    public Sales(int salesId, Drug drugSold, int quantity, double amount, String buyer, Date date, Time time) {
        this.salesId = new SimpleIntegerProperty(salesId);
        this.drugSold = new SimpleObjectProperty<>(drugSold);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.amount = new SimpleDoubleProperty(amount);
        this.buyer = new SimpleStringProperty(buyer);
        this.date = new SimpleObjectProperty<>(date);
        this.time = new SimpleObjectProperty<>(time);
    }

    // Getters and setters for salesId
    public int getSalesId() {
        return salesId.get();
    }

    public void setSalesId(int salesId) {
        this.salesId.set(salesId);
    }

    public IntegerProperty salesIdProperty() {
        return salesId;
    }

    // Getters and setters for drugSold
    public Drug getDrugSold() {
        return drugSold.get();
    }

    public void setDrugSold(Drug drugSold) {
        this.drugSold.set(drugSold);
    }

    public ObjectProperty<Drug> drugSoldProperty() {
        return drugSold;
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

    // Getters and setters for amount
    public double getAmount() {
        return amount.get();
    }

    public void setAmount(double amount) {
        this.amount.set(amount);
    }

    public DoubleProperty amountProperty() {
        return amount;
    }

    // Getters and setters for buyer
    public String getBuyer() {
        return buyer.get();
    }

    public void setBuyer(String buyer) {
        this.buyer.set(buyer);
    }

    public StringProperty buyerProperty() {
        return buyer;
    }

    // Getters and setters for date
    public Date getDate() {
        return date.get();
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }

    // Getters and setters for time
    public Time getTime() {
        return time.get();
    }

    public void setTime(Time time) {
        this.time.set(time);
    }

    public ObjectProperty<Time> timeProperty() {
        return time;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "salesId=" + getSalesId() +
                ", drugSold=" + getDrugSold() +
                ", quantity=" + getQuantity() +
                ", amount=" + getAmount() +
                ", buyer=" + getBuyer() +
                ", date=" + getDate() +
                ", time=" + getTime() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales sales = (Sales) o;
        return salesId.get() == sales.salesId.get() &&
                quantity.get() == sales.quantity.get() &&
                Double.compare(sales.amount.get(), amount.get()) == 0 &&
                Objects.equals(drugSold.get(), sales.drugSold.get()) &&
                Objects.equals(buyer.get(), sales.buyer.get()) &&
                Objects.equals(date.get(), sales.date.get()) &&
                Objects.equals(time.get(), sales.time.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesId.get(), drugSold.get(), quantity.get(), amount.get(), buyer.get(), date.get(), time.get());
    }
}
