module com.iitprojects.acaly.dx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires mysql.connector.j;

    opens com.iitprojects.acaly.dx to javafx.fxml;
    exports com.iitprojects.acaly.dx;
}
