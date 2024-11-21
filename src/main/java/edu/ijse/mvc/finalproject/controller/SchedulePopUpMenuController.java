package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.db.DBConnection;
import edu.ijse.mvc.finalproject.model.ScheduleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class SchedulePopUpMenuController implements Initializable {
    ScheduleModel scheduleModel = new ScheduleModel();
    String selectScheduleId = null;

    @FXML
    private ComboBox<String> txt;

    @FXML
    private Label txtError;

    @FXML
    void btnClose(MouseEvent event) {
        Stage window = (Stage) txt.getScene().getWindow();
        window.close();
    }

    @FXML
    void btnGenarate(ActionEvent event) throws InterruptedException {
        if (txt.getSelectionModel().getSelectedItem() != null) {
            try {
                JasperReport jasperReport = JasperCompileManager.compileReport(
                        getClass()
                                .getResourceAsStream("/report/ScheduleReport.jrxml"
                                ));

                Connection connection = DBConnection.getInstance().getConnection();

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("schedule_id", selectScheduleId);

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
        }else {
            txtError.setText("Please select Schedule Id");
            if (txtError.getText().isEmpty()) {
                Thread.sleep(2000);
                txtError.setText("");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<String> scheduleName = scheduleModel.getScheduleName();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(scheduleName);
            txt.setItems(observableList);
            txt.setOnAction(e->{
                selectScheduleId = txt.getSelectionModel().getSelectedItem();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
