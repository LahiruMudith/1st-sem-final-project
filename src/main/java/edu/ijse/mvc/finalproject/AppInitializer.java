package edu.ijse.mvc.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Buddika Fitness Center");
        stage.initStyle(StageStyle.TRANSPARENT);
//        stage.setFullScreen(true);
//        stage.setMaxWidth(100);
//        stage.centerOnScreen();
//        stage.setMaximized(true);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/pic/Group 5.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}