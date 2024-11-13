package edu.ijse.mvc.finalproject.model;

import edu.ijse.mvc.finalproject.dto.ScheduleDto;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleModel {

    public ArrayList<ScheduleDto> loadTable() throws SQLException {
        ResultSet rst = CrudUtil.execute("select s.schedule_id, s.name, s.admin_id, es.exercise_id, es.exercise_name, es.exercise_count, es.exercise_set from schedule s left join exerciseschedule es on s.schedule_id = es.schedule_id");

        ArrayList<ScheduleDto> scheduleDtos = new ArrayList<>();

        while (rst.next()){
            ScheduleDto scheduleDto = new ScheduleDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getInt(7)
            );
            scheduleDtos.add(scheduleDto);
        }
        return scheduleDtos;
    }
}
