package edu.ijse.mvc.finalproject.model;

import edu.ijse.mvc.finalproject.dto.DietPlanDto;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DietPlanModel {
    public String getNextDeitPlanId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select diet_plan_id from diet_plan order by diet_plan_id desc limit 1");


        if (rst.next()){
            String lastId = rst.getString(1);
            String substring = lastId.substring(2);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("DP%03d", newIdIndex);
        }
        return null;
    }

    public ArrayList<DietPlanDto> loadTable() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from diet_plan");

        ArrayList<DietPlanDto> dietPlanDtos = new ArrayList<>();
        while (rst.next()){
            DietPlanDto dietPlanDto = new DietPlanDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            dietPlanDtos.add(dietPlanDto);
        }
        return dietPlanDtos;
    }

    public boolean addDietPlan(DietPlanDto dietPlanDto) {
        return CrudUtil.execute("insert into diet_plan values (?,?,?,?,?)",
                dietPlanDto.getDiet_plan_id(),
                dietPlanDto.getAdmin_id(),
                dietPlanDto.getName(),
                dietPlanDto.getDuration(),
                dietPlanDto.getDescription()
                );

    }

    public boolean updateDietPlan(DietPlanDto dietPlanDto) {
        return CrudUtil.execute("update diet_plan set admin_id=?, name=?, duration=?, description=? WHERE diet_plan_id=?",
                dietPlanDto.getAdmin_id(),
                dietPlanDto.getName(),
                dietPlanDto.getDuration(),
                dietPlanDto.getDescription(),
                dietPlanDto.getDiet_plan_id()
        );
    }

    public boolean deleteDietPlan(String id) {
        return CrudUtil.execute("delete from diet_plan where diet_plan_id = ?", id);
    }

    public ArrayList<String> getDietPlanIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select diet_plan_id from diet_plan");

        ArrayList<String> dietPlanIds = new ArrayList<>();
        while (rst.next()){
            dietPlanIds.add(rst.getString(1));
        }
        return dietPlanIds;
    }
}
