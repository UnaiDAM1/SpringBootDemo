package org.example.generadordeinformes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class HelloController {
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button button;

    @FXML
    public void initialize() {
        comboBox.getItems().addAll("Entregado", "Pendiente", "Rechazado");


    }

    public void buttonActionPerformed(ActionEvent actionEvent) {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jardineria", "unai", "1234");

            Map parametros = new HashMap();
            parametros.put("EstadoPendiente", comboBox.getSelectionModel().getSelectedItem());

            System.out.println(parametros);
            JasperPrint print = JasperFillManager.fillReport("C:\\Users\\ADMIN\\IdeaProjects\\GeneradorDeInformes\\src\\main\\resources\\Informes\\Informe_jardineria.jasper", parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\ADMIN\\IdeaProjects\\GeneradorDeInformes\\src\\main\\resources\\Informes\\Informe_jardineria.pdf");
        }
        catch (Throwable e){
            e.printStackTrace();
        }
    }
}