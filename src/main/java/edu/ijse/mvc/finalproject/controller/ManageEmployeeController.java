package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.dto.*;
import edu.ijse.mvc.finalproject.dto.tm.EmployeeTM;
import edu.ijse.mvc.finalproject.model.ManageEmployeeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageEmployeeController implements Initializable {
    ManageEmployeeModel manageEmployeeModel = new ManageEmployeeModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colCenterId.setCellValueFactory(new PropertyValueFactory<>("center_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        colHireOfDate.setCellValueFactory(new PropertyValueFactory<>("date_of_hire"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        pageRefesh();
    }

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<EmployeeTM, String> colAddress;

    @FXML
    private TableColumn<EmployeeTM, Integer> colAge;

    @FXML
    private TableColumn<EmployeeTM, String> colCenterId;

    @FXML
    private TableColumn<EmployeeTM, Date> colHireOfDate;

    @FXML
    private TableColumn<EmployeeTM, String> colId;

    @FXML
    private TableColumn<EmployeeTM, String> colName;

    @FXML
    private TableColumn<EmployeeTM, String> colPhone;

    @FXML
    private TableColumn<EmployeeTM, String> colPosition;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private MenuButton txtCenterId;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private MenuButton txtPosition;

    @FXML
    void tblClick(MouseEvent event) {
        btnAdd.setDisable(true);
        btnDelete.setDisable(false);
        btnUpdate.setDisable(false);
        txtId.setEditable(false);

        EmployeeTM selectedItem = tblEmployee.getSelectionModel().getSelectedItem();
        txtCenterId.setText(selectedItem.getCenter_id());
        txtName.setText(selectedItem.getName());
        txtId.setText(selectedItem.getEmployee_id());
        txtPhoneNumber.setText(selectedItem.getPhone_number());
        txtPosition.setText(selectedItem.getPosition());
        txtAddress.setText(selectedItem.getAddress());
        txtAge.setText(String.valueOf(selectedItem.getAge()));
    }

    @FXML
    void btnAddPosition(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PositionAdd.fxml"));
        try {
            Parent load = loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(load));
            stage.setResizable(true);
            stage.initModality(Modality.WINDOW_MODAL);
            Window underWindow = btnAdd.getScene().getWindow();
            stage.initOwner(underWindow);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Position Add Menu Load Fail");
            alert.show();
        }
    }

    @FXML
    void btnAdd(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        Date date = Date.valueOf(LocalDate.now());
        String centerId = txtCenterId.getId();
        int age  = Integer.parseInt(txtAge.getText());
        String address = txtAddress.getText();
        String positionId = txtPosition.getText();

        EmployeeDto employeeDto = new EmployeeDto(
                id,
                centerId,
                name,
                phoneNumber,
                date,
                positionId,
                age,
                address
        );

        boolean b = manageEmployeeModel.addEmployee(employeeDto);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Member Added Successfully").show();
            pageRefesh();
        }
    }

    private void pageRefesh() {
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        clearPage();
        loadTable();

        try {
            txtId.setText(manageEmployeeModel.getNextEmployeeId());
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Employee Id Set Error");
            alert.show();
        }
        try {
            MenuButton paymentPlan = (MenuButton) txtCenterId;
            ArrayList<FitnessCenterDto> centerDetails = manageEmployeeModel.getCenterDetails();
            for(FitnessCenterDto centerDto : centerDetails){
                MenuItem menuItem = new MenuItem(centerDto.getCenter_name());
                menuItem.setOnAction(event -> {
                    paymentPlan.setText(centerDto.getCenter_name());
                    paymentPlan.setId(centerDto.getCenter_id());
                });
                paymentPlan.getItems().add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Center Id Set Error");
            alert.show();
        }

        try {
            MenuButton paymentPlan = (MenuButton) txtPosition;
            ArrayList<PositionItemDto> centerDetails = manageEmployeeModel.getPositions();
            for(PositionItemDto centerDto : centerDetails){
                MenuItem menuItem = new MenuItem(centerDto.getPositionName());
                menuItem.setOnAction(event -> {
                    paymentPlan.setText(centerDto.getPositionName());
                });
                paymentPlan.getItems().add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Positions Set Error");
            alert.show();
        }
    }
    private void clearPage() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtPhoneNumber.setText("");
        txtCenterId.setText("");
        txtPosition.setText("");
        txtAge.setText("");
    }

    public void loadTable() {
        try {
            ArrayList<EmployeeDto> employeeDtos = manageEmployeeModel.getEmployee();

            ObservableList<EmployeeTM> employeeTMS = FXCollections.observableArrayList();

            for (EmployeeDto dto : employeeDtos) {
                EmployeeTM employeeTM = new EmployeeTM(
                        dto.getEmployee_id(),
                        dto.getCenter_id(),
                        dto.getName(),
                        dto.getPhone_number(),
                        dto.getDate_of_hire(),
                        dto.getPosition(),
                        dto.getAge(),
                        dto.getAddress()
                );
                employeeTMS.add(employeeTM);
            }
            tblEmployee.setItems(employeeTMS);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Member Table Error");
            alert.show();
        }
    }

    @FXML
    void btnDelete(ActionEvent event) {
        boolean b = manageEmployeeModel.deleteEmployee(txtId.getText());
        new Alert(Alert.AlertType.CONFIRMATION,"Member Delete Successfully").show();
        pageRefesh();
    }

    @FXML
    void btnUpdate(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        Date date = Date.valueOf(LocalDate.now());
        String centerId = txtCenterId.getText();
        int age  = Integer.parseInt(txtAge.getText());
        String address = txtAddress.getText();
        String positionId = txtPosition.getText();

        try {
            ArrayList<FitnessCenterDto> centerDetails = manageEmployeeModel.getCenterDetails();
            for (FitnessCenterDto centerDto : centerDetails) {
                if (centerDto.getCenter_name().equals(centerId)) {
                    centerId= centerDto.getCenter_id();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Center Id Set Error");
            alert.show();
        }

        EmployeeDto employeeDto = new EmployeeDto(
                id,
                centerId,
                name,
                phoneNumber,
                date,
                positionId,
                age,
                address
        );

        boolean b = manageEmployeeModel.updateEmployee(employeeDto);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Member Update Successfully").show();
            pageRefesh();
        }
    }

}
