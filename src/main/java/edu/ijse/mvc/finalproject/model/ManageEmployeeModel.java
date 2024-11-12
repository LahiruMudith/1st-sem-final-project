package edu.ijse.mvc.finalproject.model;

import edu.ijse.mvc.finalproject.dto.EmployeeDto;
import edu.ijse.mvc.finalproject.dto.FitnessCenterDto;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageEmployeeModel {
    public String getNextEmployeeId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select employee_id from employee order by employee_id desc limit 1");

        if (resultSet.next()){
            String lastId = resultSet.getString(1);
            System.out.println(lastId);
            String substring = lastId.substring(3);
            System.out.println(substring);
            int i = Integer.parseInt(substring);
            System.out.println(i);
            int newIdIndex = i + 1;
            return String.format("EMP%03d", newIdIndex);
        }
        return null;
    }

    public ArrayList<FitnessCenterDto> getCenterDetails() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from fitness_center");
        ArrayList<FitnessCenterDto> list = new ArrayList<>();

        while (resultSet.next()){
            FitnessCenterDto centerDto = new FitnessCenterDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            list.add(centerDto);
        }
        return list;
    }
    public ArrayList<EmployeeDto> getEmployee() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from employee");

        ArrayList<EmployeeDto> employeeList = new ArrayList<>();
        while (rst.next()){
            EmployeeDto employeeDto = new EmployeeDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getString(6),
                    rst.getInt(7),
                    rst.getString(8)
            );
            employeeList.add(employeeDto);
        }
        return employeeList;
    }
    public boolean addEmployee(EmployeeDto employeeDto) {
        return CrudUtil.execute("insert into employee value (?,?,?,?,?,?,?,?)",
                employeeDto.getEmployee_id(),
                employeeDto.getCenter_id(),
                employeeDto.getName(),
                employeeDto.getPhone_number(),
                employeeDto.getDate_of_hire(),
                employeeDto.getPosition(),
                employeeDto.getAge(),
                employeeDto.getAddress()
        );
    }
}
