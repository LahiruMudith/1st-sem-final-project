package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.dto.MemberDto;
import edu.ijse.mvc.finalproject.dto.PaymentDetailDto;
import edu.ijse.mvc.finalproject.dto.tm.PaymentDetailTM;
import edu.ijse.mvc.finalproject.model.HomePageModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    HomePageModel homePageModel = new HomePageModel();
    LoginController loginController = new LoginController();

    @FXML
    private TableColumn<PaymentDetailTM, String> colDate;

    @FXML
    private TableColumn<PaymentDetailTM, String> colId;

    @FXML
    private TableColumn<PaymentDetailTM, String> colMethod;

    @FXML
    private TableColumn<PaymentDetailTM, String> colPrice;

    @FXML
    private TableView<PaymentDetailTM> tblMember;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMemberName;

    @FXML
    private ComboBox<String> txtPaymentMethod;

    @FXML
    private TextField txtPlanId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtSearchId;

    @FXML
    private Label lblError;

    @FXML
    void btnMarkFee(ActionEvent event) {
        String id = txtId.getText();
        String memberName = txtMemberName.getText();
        double price = Double.parseDouble(txtPrice.getText());
        String paymentMethod = txtPaymentMethod.getValue();
        String adminid = loginController.currentAdminId;
        Date date = Date.valueOf(LocalDate.now());
        try {
            String newPaymentPlanId = homePageModel.getNewPlanId();

            PaymentDetailDto paymentDetailDto = new PaymentDetailDto(
                    newPaymentPlanId,
                    id,
                    memberName,
                    date,
                    price,
                    paymentMethod
            );
            System.out.println(paymentDetailDto);

            boolean b = homePageModel.markFee(paymentDetailDto, adminid);
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Payment Mark Successfully").show();
                pageRefesh();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("New Plan Id Get Error");
            alert.show();
        }


    }

    private void pageRefesh() {
        txtSearchId.clear();
        txtId.clear();
        txtPlanId.clear();
        txtMemberName.clear();
        txtPrice.clear();
        txtPaymentMethod.setValue(null);
        tblMember.getItems().clear();
    }

    @FXML
    void btnSearch(MouseEvent event) throws InterruptedException {
        lblError.setText("");
        String id = txtSearchId.getText();

        try {
            MemberDto memberDto = homePageModel.searchMember(id);
            if (memberDto == null) {
                lblError.setText("Can't Find Member");
            }

            txtId.setText(memberDto.getMember_id());
            txtMemberName.setText(memberDto.getName());
            txtPlanId.setText(memberDto.getPlan_id());
            txtPrice.setText(homePageModel.getPlanId(txtPlanId.getText()));

            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Member Detail Load Error");
            alert.show();
        }
    }

    private void loadTable() {
        try {
            ArrayList<PaymentDetailTM> paymentDetailTMS = homePageModel.loadTable(txtId.getText());
            ObservableList<PaymentDetailTM> paymentDetailTMObservableList = FXCollections.observableArrayList();
            paymentDetailTMObservableList.addAll(paymentDetailTMS);
            tblMember.setItems(paymentDetailTMObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Member Table Load Error");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("payment_id"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("payment_method"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("payment_date"));

        ObservableList<String> methods = FXCollections.observableArrayList("Bank Transfer", "Cash");
        txtPaymentMethod.setItems(methods);
        txtPaymentMethod.setOnAction(e->{
            String method = txtPaymentMethod.getSelectionModel().getSelectedItem();
            System.out.println(method);
        });
    }
}
