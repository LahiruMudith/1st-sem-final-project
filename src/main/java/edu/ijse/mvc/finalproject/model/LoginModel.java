package edu.ijse.mvc.finalproject.model;

import edu.ijse.mvc.finalproject.dto.AdminDto;
import edu.ijse.mvc.finalproject.util.CrudUtil;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginModel {
    @SneakyThrows
    public ArrayList<AdminDto> getAdminsData() {
        ResultSet rst =CrudUtil.execute("select * from admin");

        ArrayList<AdminDto> admins = new ArrayList<>();

        while (rst.next()){
            AdminDto adminDto = new AdminDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            admins.add(adminDto);
        }
        return admins;
    }
}
