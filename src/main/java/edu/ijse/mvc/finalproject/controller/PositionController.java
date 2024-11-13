package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.dto.PositionItemDto;
import edu.ijse.mvc.finalproject.model.ManageEmployeeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class PositionController {

    @FXML
    private Text txtError;

    @FXML
    private TextField txtPositionName;

    @FXML
    void btnAddPosition(ActionEvent event) {
        ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();

        PositionItemDto positionItemDto = new PositionItemDto(txtPositionName.getText());
        boolean b = manageEmployeeModel.setPosition(positionItemDto);
        if (b){
            Stage window = (Stage) txtError.getScene().getWindow();
            window.close();
        }else {
            txtError.setText("Position not added");
            txtPositionName.clear();
        }
    }

    @FXML
    void btnClose(MouseEvent event) {
        Stage window = (Stage) txtError.getScene().getWindow();
        window.close();
    }

}
