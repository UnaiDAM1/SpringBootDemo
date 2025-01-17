module org.example.generadordeinformes {
    requires javafx.controls;
    requires javafx.fxml;
    requires net.sf.jasperreports.core;
    requires java.desktop;
    requires java.sql;


    opens org.example.generadordeinformes to javafx.fxml;
    exports org.example.generadordeinformes;
}