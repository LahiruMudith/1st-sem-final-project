package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.db.DBConnection;
import edu.ijse.mvc.finalproject.dto.PositionItemDto;
import edu.ijse.mvc.finalproject.model.DietPlanModel;
import edu.ijse.mvc.finalproject.model.ManageEmployeeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DietPlanReportPopUpMenuController implements Initializable {
    DietPlanModel dietPlanModel = new DietPlanModel();
    String selectedCusId = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<String> dietPlanIds = dietPlanModel.getDietPlanIds();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            observableList.addAll(dietPlanIds);
            txtDietPlanId.setItems(observableList);
            txtDietPlanId.setOnAction(e->{
                selectedCusId = txtDietPlanId.getSelectionModel().getSelectedItem();
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private ComboBox<String> txtDietPlanId;

    @FXML
    private Text txtError;

    @FXML
    void btnGenarate(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/report/DietPlanReport.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("diet_plan_id", selectedCusId);

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

    @FXML
    void btnClose(MouseEvent event) {
        Stage window = (Stage) txtDietPlanId.getScene().getWindow();
        window.close();
    }

}
