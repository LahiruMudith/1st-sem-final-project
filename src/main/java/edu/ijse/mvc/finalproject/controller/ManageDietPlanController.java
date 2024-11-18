package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.dto.DietPlanDto;
import edu.ijse.mvc.finalproject.dto.tm.DietPlanTM;
import edu.ijse.mvc.finalproject.model.DietPlanModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ManageDietPlanController implements Initializable {
    DietPlanModel dietPlanModel = new DietPlanModel();
    private ObservableList<DietPlanTM> observableList = FXCollections.observableArrayList();
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<DietPlanTM> tblDeitPlan;

    @FXML
    private TableColumn<DietPlanTM, String> colId;

    @FXML
    private TableColumn<DietPlanTM, String> colName;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAdminId;

    @FXML
    void btnAdd(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String adminId = txtAdminId.getText();
        String description = txtDescription.getText();
        String duration = txtDuration.getText() + " days";

        DietPlanDto dietPlanDto = new DietPlanDto(id, adminId, name, duration, description);
        System.out.println(dietPlanDto);
        boolean b = dietPlanModel.addDietPlan(dietPlanDto);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Diet Plan Added Successfully").show();
            pageRefesh();
        }
    }

    @FXML
    void btnDelete(ActionEvent event) {
        String id = txtId.getText();
        boolean b = dietPlanModel.deleteDietPlan(id);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Diet Plan Delete Successfully").show();
            pageRefesh();
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String adminId = txtAdminId.getText();
        String description = txtDescription.getText();
        String duration = txtDuration.getText() + " days";

        DietPlanDto dietPlanDto = new DietPlanDto(id, adminId, name, duration, description);
        System.out.println(dietPlanDto);
        boolean b = dietPlanModel.updateDietPlan(dietPlanDto);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Diet Plan Update Successfully").show();
            pageRefesh();
        }
    }

    @FXML
    void tblCLick(MouseEvent event) {
        btnAdd.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);

        DietPlanTM selectedItem = tblDeitPlan.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);

        txtId.setText(selectedItem.getDiet_plan_id());
        txtName.setText(selectedItem.getName());
        txtDuration.setText(selectedItem.getDuration());
        txtDescription.setText(selectedItem.getDescription());
        txtAdminId.setText(selectedItem.getAdmin_id());
    }


    private void pageRefesh() {
        btnAdd.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        try {
            txtId.setText(dietPlanModel.getNextDeitPlanId());
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Diet Plan Id Set Error");
            alert.show();
        }

        loadTable();
    }
    public void loadTable() {
        try {
            ArrayList<DietPlanDto> dietPlanDtos = dietPlanModel.loadTable();

            observableList = FXCollections.observableArrayList();

            for (DietPlanDto dietPlanDto : dietPlanDtos) {
                DietPlanTM dietPlanTM = new DietPlanTM(
                        dietPlanDto.getDiet_plan_id(),
                        dietPlanDto.getAdmin_id(),
                        dietPlanDto.getName(),
                        dietPlanDto.getDuration(),
                        dietPlanDto.getDescription()
                );
                observableList.add(dietPlanTM);
            }
            tblDeitPlan.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Table Load Error Error");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("diet_plan_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        pageRefesh();
    }

}
