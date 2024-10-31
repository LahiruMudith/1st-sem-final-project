package edu.ijse.mvc.finalproject.model;

import edu.ijse.mvc.finalproject.dto.SignInDto;
import edu.ijse.mvc.finalproject.util.CrudUtil;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignInModel {
    public String getNextAdminId(){
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            String sql = "select admin_id from admin order by admin_id desc limit 1";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = CrudUtil.execute("select admin_id from admin order by admin_id desc limit 1");
            if(resultSet.next()){
                String lastId = resultSet.getString(1);
                String subString = lastId.substring(1);
                int i =Integer.parseInt(subString);
                int newAdminIndex = i+1;
                return String.format("A%03d", newAdminIndex);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getMessage());
            alert.show();
        }
        return "000";
    }

    public boolean SaveAdmin(SignInDto signInDto) {
//        Connection connection = DBConnection.getInstance().getConnection();
//        try {
//            connection.setAutoCommit(false);
//            String adminSql = "insert into admin values(?,?,?,?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(adminSql);
//            preparedStatement.setString(1, signInDto.getAdminId());
//            preparedStatement.setString(2, signInDto.getName());
//            preparedStatement.setString(3, signInDto.getAddress());
//            preparedStatement.setString(4, signInDto.getPassword());

            boolean isSaveAdmin = CrudUtil.execute("insert into admin values(?,?,?,?)", signInDto.getAdminId(), signInDto.getName(), signInDto.getAddress(), signInDto.getPassword());
            return isSaveAdmin;
    }
}
