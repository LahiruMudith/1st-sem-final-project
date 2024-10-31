package edu.ijse.mvc.finalproject.util;

import edu.ijse.mvc.finalproject.db.DBConnection;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String sql, Object... obj){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i=0; i<obj.length; i++) {
                preparedStatement.setObject((i + 1), obj[i]);
            }

            if (sql.startsWith("select") || sql.startsWith("SELECT")){
                ResultSet resultSet = preparedStatement.executeQuery();
                return (T) resultSet;
            }else {
                int i = preparedStatement.executeUpdate();
                boolean isSaved = i>0;
                return (T) ((Boolean)isSaved);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getMessage());
            alert.show();
        }
        return (T)"Crud Util Error";
    }
}
