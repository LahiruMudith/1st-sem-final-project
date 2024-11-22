package edu.ijse.mvc.finalproject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ReportGenaratorController {

    @FXML
    private AnchorPane pane;

    @FXML
    private Pane paneTemplate;

    @FXML
    private Pane paneTemplate1;

    @FXML
    private Pane paneTemplate11;

    @FXML
    private Pane paneTemplate2;

    public void genarateDietPlanReport(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/report/DietPlanReportPopUpMenu.fxml"));
        try {
            Parent load = loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(load));
            stage.setResizable(true);
            stage.initModality(Modality.WINDOW_MODAL);
            Window underWindow = pane.getScene().getWindow();
            stage.initOwner(underWindow);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Diet Plan Pop Up Menu Load Fail");
            alert.show();
        }
    }
    @FXML
    void genarateScheduleReport(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/report/ScheduleReportPopUpMenu.fxml"));
        try {
            Parent load = loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(load));
            stage.setResizable(true);
            stage.initModality(Modality.WINDOW_MODAL);
            Window underWindow = pane.getScene().getWindow();
            stage.initOwner(underWindow);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Diet Plan Pop Up Menu Load Fail");
            alert.show();
        }
    }

    @FXML
    void genaratePaymentReport(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/report/PaymentReportPopUpMenu.fxml"));
        try {
            Parent load = loader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(load));
            stage.setResizable(true);
            stage.initModality(Modality.WINDOW_MODAL);
            Window underWindow = pane.getScene().getWindow();
            stage.initOwner(underWindow);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Payment Pop Up Menu Load Fail");
            alert.show();
        }
    }

}
