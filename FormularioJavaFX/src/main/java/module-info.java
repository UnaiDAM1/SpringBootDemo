module com.example.formulariojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.formulariojavafx to javafx.fxml;
    exports com.example.formulariojavafx;
}