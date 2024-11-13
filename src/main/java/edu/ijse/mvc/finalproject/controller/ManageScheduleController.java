package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.dto.ScheduleDto;
import edu.ijse.mvc.finalproject.dto.tm.ScheduleTM;
import edu.ijse.mvc.finalproject.model.ScheduleModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageScheduleController implements Initializable {
    ScheduleModel scheduleModel = new ScheduleModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<ScheduleTM, String>("schedule_id"));
        colName.setCellValueFactory(new PropertyValueFactory<ScheduleTM, String>("name"));

        pageRefesh();
    }

    private void pageRefesh() {
        btnAdd.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        loadTable();
    }

    private void loadTable() {
        try {
            ArrayList<ScheduleDto> scheduleDtos = scheduleModel.loadTable();
            ObservableList<ScheduleTM> dtos = FXCollections.observableArrayList();
            for (ScheduleDto scheduleDto : scheduleDtos) {
                ScheduleTM scheduleTM = new ScheduleTM(
                        scheduleDto.getSchedule_id(),
                        scheduleDto.getName(),
                        scheduleDto.getAdmin_id(),
                        scheduleDto.getExercise_id(),
                        scheduleDto.getExercise_name(),
                        scheduleDto.getExercise_count(),
                        scheduleDto.getExercise_set()
                );
                System.out.println(scheduleTM);
                dtos.add(scheduleTM);
            }
            tblMember.setItems(dtos);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Schedule Table Load Error");
            alert.show();
        }
    }

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<ScheduleTM, String> colId;

    @FXML
    private TableColumn<ScheduleTM, String> colName;

    @FXML
    private TableView<ScheduleTM> tblMember;

    @FXML
    private TextField txtAddress;

    @FXML
    private MenuButton txtAdminId;

    @FXML
    private TextField txtCount;

    @FXML
    private TextField txtDescription;

    @FXML
    private MenuButton txtExercise;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSet;

    @FXML
    void btnAdd(ActionEvent event) {

    }

    @FXML
    void btnAddExercise(MouseEvent event) {

    }

    @FXML
    void btnDelete(ActionEvent event) {

    }

    @FXML
    void btnUpdate(ActionEvent event) {

    }

}
