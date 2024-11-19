package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.db.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ReportGenaratorController {

    @FXML
    private Pane paneTemplate;

    @FXML
    private Pane paneTemplate1;

    @FXML
    private Pane paneTemplate11;

    @FXML
    private Pane paneTemplate2;

    @FXML
    void genarateDietPlanReport(MouseEvent event) {
        System.out.println("Run");
        String id = "DP001";
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/report/DietPlanReport.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("diet_plan_id", id);

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
//           e.printStackTrace();
        }
    }

}
