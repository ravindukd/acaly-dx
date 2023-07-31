module com.iitprojects.acaly.dx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.iitprojects.acaly.dx to javafx.fxml;
    exports com.iitprojects.acaly.dx;
}
