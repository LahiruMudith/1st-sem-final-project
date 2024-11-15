package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.dto.*;
import edu.ijse.mvc.finalproject.dto.tm.MemberTM;
import edu.ijse.mvc.finalproject.model.ManageMemberModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.VirtualFlow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

public class ManageMemberController implements Initializable {
    ManageMemberModel manageMemberModel = new ManageMemberModel();
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<MemberTM, Double> colHeight;

    @FXML
    private TableColumn<MemberTM, String> colAddress;

    @FXML
    private TableColumn<MemberTM, String> colEmail;

    @FXML
    private TableColumn<MemberTM, Date> colRegisterDate;

    @FXML
    private TableColumn<MemberTM, String> colId;

    @FXML
    private TableColumn<MemberTM, String> colName;

    @FXML
    private TableColumn<MemberTM, String> colPaymentPlan;

    @FXML
    private TableColumn<MemberTM, String> colPhone;

    @FXML
    private TableColumn<MemberTM, String> colDietPlan;

    @FXML
    private TableColumn<MemberTM, String> colScheduleName;

    @FXML
    private TableColumn<MemberTM, Double> colWeight;

    @FXML
    private TableView<MemberTM> tblMember;

    @FXML
    private TextField txtAddress;

    @FXML
    private MenuButton txtDietPlanId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtHeight;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private MenuButton txtPaymentPlan;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private MenuButton txtScheduleId;

    @FXML
    private TextField txtWeight;

    @FXML
    void btnAdd(ActionEvent event) {
        String name = txtName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        Date registrationDate = Date.valueOf(LocalDate.now());
        String scheduleId = txtScheduleId.getId();
        Double weight = Double.parseDouble(txtWeight.getText());
        String dietPlanId = txtDietPlanId.getId();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        Double height = Double.parseDouble(txtHeight.getText());
        String id = txtId.getText();
        String paymentPlan = txtPaymentPlan.getId();

        MemberDto memberDto = new MemberDto(
                id,
                name,
                address,
                phoneNumber,
                email,
                registrationDate,
                weight,
                height,
                scheduleId,
                paymentPlan,
                dietPlanId
        );
        boolean b = manageMemberModel.addMember(memberDto);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Member Added Successfully").show();
            pageRefesh();
        }
    }

    @FXML
    void btnDelete(ActionEvent event) {
        String id = txtId.getText();
        boolean b = manageMemberModel.deleteMember(id);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Member Delete Successfully").show();
            pageRefesh();
        }
    }

    @FXML
    void btnUpdate(ActionEvent event) {
        String name = txtName.getText();
        String phoneNumber = txtPhoneNumber.getText();
        Date registrationDate = Date.valueOf(LocalDate.now());
        String scheduleId = txtScheduleId.getText();
        Double weight = Double.parseDouble(txtWeight.getText());
        String dietPlanId = txtDietPlanId.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        Double height = Double.parseDouble(txtHeight.getText());
        String id = txtId.getText();
        String paymentPlan = txtPaymentPlan.getText();

        try {
            ArrayList<ExerciseScheduleDto> schedule = manageMemberModel.getSchedule();
            for (ExerciseScheduleDto exerciseScheduleDto : schedule) {
                if (exerciseScheduleDto.getSchedule_id().equals(scheduleId)) {
                    scheduleId = exerciseScheduleDto.getSchedule_id();
                }
            }
            ArrayList<PaymentPlanDto> paymentPlanDtos = manageMemberModel.getPaymentPlan();
            for (PaymentPlanDto paymentPlanDto : paymentPlanDtos) {
                if (paymentPlanDto.getPlan_name().equals(paymentPlan)) {
                    paymentPlan = paymentPlanDto.getPlan_id();
                }
            }
            ArrayList<DietPlanDto>  dietPlanDtos = manageMemberModel.getDietPlan();
            for (DietPlanDto dietPlanDto : dietPlanDtos) {
                if (dietPlanDto.getName().equals(dietPlanId)) {
                    dietPlanId = dietPlanDto.getDiet_plan_id();
                }
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Menu Item Set Error");
            alert.show();
        }

        MemberDto memberDto = new MemberDto(
                id,
                name,
                address,
                phoneNumber,
                email,
                registrationDate,
                weight,
                height,
                scheduleId,
                paymentPlan,
                dietPlanId
        );
        boolean b = manageMemberModel.updateMember(memberDto);
        if (b){
            new Alert(Alert.AlertType.CONFIRMATION,"Member Update Successfully").show();
            pageRefesh();
        }
    }

    @FXML
    void tblClick(MouseEvent event) {
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
        btnAdd.setDisable(true);
        MemberTM memberTM= tblMember.getSelectionModel().getSelectedItem();

        txtId.setText(memberTM.getMember_id());
        txtId.setEditable(false);
        txtName.setText(memberTM.getName());
        txtAddress.setText(memberTM.getAddress());
        txtPhoneNumber.setText(memberTM.getPhone_number());
        txtEmail.setText(memberTM.getEmail());
        txtWeight.setText(String.valueOf(memberTM.getWeight()));
        txtHeight.setText(String.valueOf(memberTM.getHeight()));
        txtScheduleId.setText(memberTM.getSchedule_name());
        txtPaymentPlan.setText(memberTM.getPlan_name());
        txtDietPlanId.setText(memberTM.getDiet_plan_name());
    }


    public void loadTable(){
        try {
            ArrayList<MemberDto> memberDto = manageMemberModel.getMember();

            ObservableList<MemberTM> memberTMS = FXCollections.observableArrayList();

            for (MemberDto dto : memberDto) {
                MemberTM memberTM = new MemberTM(
                        dto.getMember_id(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getPhone_number(),
                        dto.getEmail(),
                        dto.getRegister_date(),
                        dto.getWeight(),
                        dto.getHeight(),
                        dto.getSchedule_id(),
                        dto.getPlan_id(),
                        dto.getDiet_plan_id()
                );
                memberTMS.add(memberTM);
            }
            tblMember.setItems(memberTMS);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Member Table Error");
            alert.show();
        }
    }

    public void pageRefesh(){
        clearPage();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

        try {
            txtId.setText(manageMemberModel.getNextMemberId());

            MenuButton paymentPlan = (MenuButton) txtPaymentPlan;
            ArrayList<PaymentPlanDto> paymentPlanList = manageMemberModel.getPaymentPlan();
            for(PaymentPlanDto paymentDto : paymentPlanList){
                MenuItem menuItem = new MenuItem(paymentDto.getPlan_name());
                menuItem.setOnAction(event -> {
                    paymentPlan.setText(paymentDto.getPlan_name());
                    paymentPlan.setId(paymentDto.getPlan_id());
                });
                paymentPlan.getItems().add(menuItem);
            }

            MenuButton schedule = (MenuButton) txtScheduleId;
            ArrayList<ExerciseScheduleDto> scheduleList = manageMemberModel.getSchedule();
            for(ExerciseScheduleDto exerciseScheduleDto : scheduleList){
                MenuItem menuItem = new MenuItem(exerciseScheduleDto.getSchedule_name());
                menuItem.setOnAction(event -> {
                    schedule.setText(exerciseScheduleDto.getSchedule_name());
                    schedule.setId(exerciseScheduleDto.getSchedule_id());
                });
                schedule.getItems().add(menuItem);
            }

            MenuButton dietPlan = (MenuButton) txtDietPlanId;
            ArrayList<DietPlanDto> dietPlanName = manageMemberModel.getDietPlan();
            for(DietPlanDto dietPlanDto : dietPlanName){
                MenuItem menuItem = new MenuItem(dietPlanDto.getName());
                menuItem.setOnAction(event -> {
                    dietPlan.setText(dietPlanDto.getName());
                    dietPlan.setId(dietPlanDto.getDiet_plan_id());
                });
                dietPlan.getItems().add(menuItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Menu Item Set Error");
            alert.show();
        }
        try {

            txtId.setText(manageMemberModel.getNextMemberId());
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Member Id Set Error");
            alert.show();
        }
        loadTable();
    }

    public void clearPage(){
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtPhoneNumber.setText("");
        txtEmail.setText("");
        txtWeight.setText("");
        txtHeight.setText("");
        txtScheduleId.setText("");
        txtPaymentPlan.setText("");
        txtDietPlanId.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone_number"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRegisterDate.setCellValueFactory(new PropertyValueFactory<>("register_date"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colHeight.setCellValueFactory(new PropertyValueFactory<>("height"));
        colScheduleName.setCellValueFactory(new PropertyValueFactory<>("schedule_name"));
        colPaymentPlan.setCellValueFactory(new PropertyValueFactory<>("plan_name"));
        colDietPlan.setCellValueFactory(new PropertyValueFactory<>("diet_plan_name"));

        pageRefesh();
    }
}
