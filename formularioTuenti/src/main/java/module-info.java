module com.example.formulariotuenti {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.formulariotuenti to javafx.fxml;
    exports com.example.formulariotuenti;
}