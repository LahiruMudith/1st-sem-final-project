package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.dto.*;
import edu.ijse.mvc.finalproject.dto.tm.EmployeeTM;
import edu.ijse.mvc.finalproject.model.ManageEmployeeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private TextField txtPosition;


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

    }

    @FXML
    void btnUpdate(ActionEvent event) {

    }

}
