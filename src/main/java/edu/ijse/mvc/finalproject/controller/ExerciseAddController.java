package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.dto.ExerciseDto;
import edu.ijse.mvc.finalproject.dto.PositionItemDto;
import edu.ijse.mvc.finalproject.model.ManageEmployeeModel;
import edu.ijse.mvc.finalproject.model.ScheduleModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ExerciseAddController implements Initializable {
    ScheduleModel scheduleModel = new ScheduleModel();
    @FXML
    private Text txtError;

    @FXML
    private TextField txtDes;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    void btnAddPosition(ActionEvent event) {
        ScheduleModel scheduleModel = new ScheduleModel();

        ExerciseDto exerciseDto = new ExerciseDto(
                txtName.getText(),
                txtId.getText(),
                txtDes.getText()
        );
        boolean b = scheduleModel.addExercise(exerciseDto);
        if (b){
            Stage window = (Stage) txtError.getScene().getWindow();
            window.close();
        }else {
            txtError.setText("Exercise not added");
            txtName.clear();
            txtId.clear();
            txtDes.clear();
        }
    }

    @FXML
    void btnClose(MouseEvent event) {
        Stage window = (Stage) txtError.getScene().getWindow();
        window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            txtName.setText(scheduleModel.getNextExerciseId());
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Exercise Id Set Error");
            alert.show();
        }
    }
}
