package edu.ijse.mvc.finalproject.controller;

import edu.ijse.mvc.finalproject.AppInitializer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    LoginController loginController = new LoginController();
    @FXML
    private Pane bodyPane;

    @FXML
    private ImageView btnMinimize;

    @FXML
    private Label lblAdminId;

    @FXML
    private Label lblAdminName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Pane menuPane;

    @FXML
    private Button btnManageDietPlan;

    @FXML
    private Button btnManageEmployee;

    @FXML
    private Button btnManageMember;

    @FXML
    private Button btnManageReportGenarator;

    @FXML
    private Button btnManageSchedule;

    @FXML
    private Button btnHome;

    public void btnClose(MouseEvent mouseEvent) {
        System.exit(0);
    }
    public void btnMinimize(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }
    public void btnMaximize(MouseEvent mouseEvent) {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(false);
        }else {
            stage.setMaximized(true);
        }
    }

    @FXML
    void btnGoToHome(MouseEvent event) {
        setColour(btnHome);
        navigation("/view/HomePage.fxml");
    }

    @FXML
    void btnGoToManageDietPlan(MouseEvent event) {
        setColour(btnManageDietPlan);
        navigation("/view/ManageDietPlan.fxml");
    }

    @FXML
    void btnGoToManageEmployee(MouseEvent event) {
        setColour(btnManageEmployee);
        navigation("/view/ManageEmployee.fxml");
    }

    @FXML
    void btnGoToManageMember(MouseEvent event) {
        setColour(btnManageMember);
        navigation("/view/ManageMember.fxml");
    }

    @FXML
    void btnGoToManageSchedule(MouseEvent event) {
        setColour(btnManageSchedule);
        navigation("/view/ManageSchedule.fxml");
    }

    @FXML
    void btnGoToReportGenarate(MouseEvent event) {
        setColour(btnManageReportGenarator);
        navigation("/view/ReportGenarator.fxml");
    }

    @FXML
    void btnSetting(MouseEvent event) {

    }

    private void setColour(Button event) {
        ArrayList<Button> btns = new ArrayList(List.of(btnHome,btnManageMember,btnManageSchedule,btnManageEmployee,btnManageDietPlan));
        String defaultColour = "-fx-background-color: none";
        for (Button btn : btns){
            btn.setStyle(defaultColour);
        }

        String inactiveStyle = "-fx-background-color: #C3D8CA";
        event.setStyle(inactiveStyle);
    }

    public void navigation(String path){
        try {
            bodyPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(path));
            load.prefWidthProperty().bind(bodyPane.widthProperty());
            load.prefHeightProperty().bind(bodyPane.heightProperty());
            bodyPane.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bodyPane.getChildren().clear();
        lblAdminId.setText(loginController.currentAdminId);
        lblAdminName.setText(loginController.currentAdminName);

        try {
            AnchorPane load = FXMLLoader.load(getClass().getResource("/view/HomePage.fxml"));
            load.prefWidthProperty().bind(bodyPane.widthProperty());
            load.prefHeightProperty().bind(bodyPane.heightProperty());
            bodyPane.getChildren().add(load);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e ->
                lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String date = sdf.format(new Date());
        lblDate.setText(date);
    }

    public void btnLogOut(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage1 = new Stage();
        stage1.initStyle(StageStyle.TRANSPARENT);
        stage1.getIcons().add(new Image(getClass().getResourceAsStream("/assets/pic/Group 5.png")));
        stage1.setScene(scene);

        Stage window = (Stage) btnHome.getScene().getWindow();
        window.close();
        stage1.show();
    }
}
