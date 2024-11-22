package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.fill.*;

public class PaymentPopUpMenuController {

    @FXML
    private Label txtError;

    @FXML
    private DatePicker txtFromDate;

    @FXML
    private DatePicker txtToDate;

    @FXML
    void btnClose(MouseEvent event) {
        Stage window = (Stage) txtError.getScene().getWindow();
        window.close();
    }

    @FXML
    void btnGenarate(ActionEvent event) throws InterruptedException {
        String fromDate = String.valueOf(txtFromDate.getValue());
        String toDate = String.valueOf(txtToDate.getValue());

        if (true) {
            try {
                JasperReport jasperReport = JasperCompileManager.compileReport(
                        getClass()
                                .getResourceAsStream("/report/PaymentReport.jrxml"
                                ));

                Connection connection = DBConnection.getInstance().getConnection();

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("from_date", fromDate);
                parameters.put("to_date", toDate);

                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        jasperReport,
                        parameters,
                        connection
                );

                JasperViewer.viewReport(jasperPrint, false);
            } catch (JRException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
            }
        }else {
            txtError.setText("Please Select Date");
            if (txtError.getText().isEmpty()) {
                Thread.sleep(2000);
                txtError.setText("");
            }
        }
    }
}
