module com.example.pharmacy_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    exports  com.example.pharmacy_management_system.controllers;
    opens com.example.pharmacy_management_system to javafx.fxml;
    opens  com.example.pharmacy_management_system.controllers to javafx.fxml;
    opens com.example.pharmacy_management_system.models to javafx.base;
    exports com.example.pharmacy_management_system;
}