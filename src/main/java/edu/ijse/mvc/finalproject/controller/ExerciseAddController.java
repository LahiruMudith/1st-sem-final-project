package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.dto.ExerciseDto;
import edu.ijse.mvc.finalproject.dto.PositionItemDto;
import edu.ijse.mvc.finalproject.model.ManageEmployeeModel;
import edu.ijse.mvc.finalproject.model.ScheduleModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExerciseAddController {

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
                txtId.getText(),
                txtName.getText(),
                txtDes.getText()
        );
        boolean b = scheduleModel.addExercise(exerciseDto);
        if (b){
            Stage window = (Stage) txtError.getScene().getWindow();
            window.close();
        }else {
            txtError.setText("Exercise not added");
            txtId.clear();
            txtName.clear();
            txtDes.clear();
        }
    }

    @FXML
    void btnClose(MouseEvent event) {
        Stage window = (Stage) txtError.getScene().getWindow();
        window.close();
    }

}
