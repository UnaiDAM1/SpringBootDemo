package com.example.formulariotuenti;

/*
 * Clase que controla la segunda página y muestra los datos mandados por la principal
 *
 * @Autor: Unai Nieto
 * */

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import java.util.HashMap;


public class ControllerDos {

    @FXML
    private Label salida;

    // Metodo para recoger los datos e imprimirlos en el label
    public void recogerDatos(HashMap<String,String> datos){
        String resultado = "Nombre: " + datos.get("Nombre")+"\n" +
                "Apellidos: " + datos.get("Apellidos") + "\n" +
                "Correo: " + datos.get("Correo") + "\n" +
                "Telefono: " + datos.get("Telefono") + "\n" +
                "Sexo: " + datos.get("Sexo") + "\n" +
                "Fecha de nacimiento: " + datos.get("Fecha de nacimiento") + "\n";
        salida.setText(resultado);

    }

}
