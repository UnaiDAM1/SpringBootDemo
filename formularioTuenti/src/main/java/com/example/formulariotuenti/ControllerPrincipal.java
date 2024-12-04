package com.example.formulariotuenti;

/*
 * Clase que controla la recogida de datos de la aplicación
 *
 * @Autor: Unai Nieto
 * */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

public class ControllerPrincipal {

    //Declaración de componentes
    @FXML
    private Pane pane;

    @FXML
    private ImageView imgTuenti;

    @FXML
    private MenuButton idiomaMenuButton;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField apellidosField;

    @FXML
    private TextField correoField;

    @FXML
    private RadioButton hombreRadioButton;

    @FXML
    private RadioButton mujerRadioButton;

    @FXML
    private ComboBox<String> diaComboBox;

    @FXML
    private ComboBox<String> mesComboBox;

    @FXML
    private ComboBox<String> anioComboBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> prefijoComboBox;

    @FXML
    private TextField telefonoField;

    @FXML
    private CheckBox terminosCheckBox;

    @FXML
    private Button enviarButton;

    // Metodo que inicializa los combobox
    @FXML
    public void initialize() {
        MenuItem itemEspanol = new MenuItem("Español");
        MenuItem itemIngles = new MenuItem("Inglés");

        itemEspanol.setOnAction(e -> idiomaMenuButton.setText("Español"));
        itemIngles.setOnAction(e -> idiomaMenuButton.setText("Inglés"));

        idiomaMenuButton.getItems().addAll(itemEspanol, itemIngles);

        diaComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
        mesComboBox.getItems().addAll("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        anioComboBox.getItems().addAll("2024", "2023", "2022", "2021");
        prefijoComboBox.getItems().add("+34E");

    }

    // Metodo para darle funcionalidad a los hiperlinks
    @FXML
    private void handleHipervinculos(){
        try{
            URI uri = new URI("http://www.ticarte.com./");
            Desktop.getDesktop().browse(uri);
        } catch (IOException |URISyntaxException e ) {
            throw new RuntimeException(e);
        }
    }

    // Metodo que al enviar guarda todo en un hashmap para mandarselo a la segunda pagina
    @FXML
    private void handleEnviar() throws IOException {
        // Con este if hacemos que si algun campo está vacio salte un error
        if (nombreField.getText().isEmpty() || correoField.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Por favor, completa todos los campos obligatorios.");
            alerta.show();
            return;
        }
        HashMap<String, String> datos = new HashMap<>();
        datos.put("Nombre", nombreField.getText());
        datos.put("Apellidos", apellidosField.getText());
        datos.put("Correo", correoField.getText());
        datos.put("Telefono", telefonoField.getText());
        if (hombreRadioButton.isSelected()) {
            datos.put("Sexo", hombreRadioButton.getText());
        } else if (mujerRadioButton.isSelected()) {
            datos.put("Sexo", mujerRadioButton.getText());
        }
        datos.put("Fecha de nacimiento", diaComboBox.getValue() + "/" + mesComboBox.getValue() + "/" + anioComboBox.getValue());
        datos.put("Contraseña", passwordField.getText());

        // Iniciamos la otra pagina y mandamos los datos
        try{
            FXMLLoader cargarSegundaPagina = new FXMLLoader(getClass().
                    getResource("/com/example/formulariotuenti/segunda-pagina.fxml"));

            Parent root = cargarSegundaPagina.load();
            ControllerDos controllerDos = cargarSegundaPagina.getController();
            // Mandar datos
            controllerDos.recogerDatos(datos);
            // Inicializacion de la nueva página
            Stage escenaSecundaria = new Stage();
            escenaSecundaria.initModality(Modality.APPLICATION_MODAL);
            escenaSecundaria.setScene(new Scene(root));
            escenaSecundaria.showAndWait();

        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}