package edu.ijse.mvc.finalproject.model;

import edu.ijse.mvc.finalproject.db.DBConnection;
import edu.ijse.mvc.finalproject.dto.AdminDto;
import edu.ijse.mvc.finalproject.dto.ExerciseDto;
import edu.ijse.mvc.finalproject.dto.ExerciseScheduleDto;
import edu.ijse.mvc.finalproject.dto.ScheduleDto;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleModel {

//    public ArrayList<ExerciseScheduleDto> loadTable() throws SQLException {
//        ResultSet rst = CrudUtil.execute("select * from exerciseschedule");
//
//        ArrayList<ExerciseScheduleDto> exerciseScheduleDtos = new ArrayList<>();
//
//        while (rst.next()){
//            ExerciseScheduleDto exerciseScheduleDto = new ExerciseScheduleDto(
//                    rst.getString(1),
//                    rst.getString(2),
//                    rst.getString(3),
//                    rst.getString(4),
//                    rst.getInt(5),
//                    rst.getInt(6),
//                    rst.getString(7)
//            );
//            exerciseScheduleDtos.add(exerciseScheduleDto);
//        }
//        return exerciseScheduleDtos;
//    }

    public String getNextScheduleId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select schedule_id from schedule order by schedule_id desc limit 1");

        if (resultSet.next()){
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(3);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("SCH%03d", newIdIndex);
        }
        return null;
    }

    public ArrayList<ExerciseDto> getExercise() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from exercise");

        ArrayList<ExerciseDto> exerciseDtos = new ArrayList<>();

        while (rst.next()){
            ExerciseDto exerciseDto = new ExerciseDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
            exerciseDtos.add(exerciseDto);
        }
        return exerciseDtos;
    }

    public ArrayList<AdminDto> getAdmin() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from admin");

        ArrayList<AdminDto> adminDtos = new ArrayList<>();

        while(rst.next()){
            AdminDto adminDto = new AdminDto(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            adminDtos.add(adminDto);
        }
        return adminDtos;
    }

    public boolean addSchedule(ScheduleDto scheduleDto, ArrayList<ExerciseScheduleDto> exerciseScheduleDtos) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);

            boolean isSavedSheduleTable = CrudUtil.execute("insert into schedule VALUES(?,?,?)",
                    scheduleDto.getSchedule_id(),
                    scheduleDto.getName(),
                    scheduleDto.getAdmin_id()
            );
            if (isSavedSheduleTable){
                boolean isSavedExerciseSheduleTable = false;
                for (ExerciseScheduleDto exerciseScheduleDto : exerciseScheduleDtos){
                    isSavedExerciseSheduleTable = CrudUtil.execute("INSERT INTO exerciseSchedule VALUES(?,?,?,?,?,?,?)",
                            scheduleDto.getSchedule_id(),
                            exerciseScheduleDto.getExercise_id(),
                            exerciseScheduleDto.getExercise_name(),
                            exerciseScheduleDto.getSchedule_name(),
                            exerciseScheduleDto.getExercise_count(),
                            exerciseScheduleDto.getExercise_set(),
                            exerciseScheduleDto.getAdmin_id()
                    );
                }
                if (isSavedExerciseSheduleTable){
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        }catch (Exception e){
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    public boolean addExercise(ExerciseDto exerciseDto) {
        return CrudUtil.execute("INSERT INTO exercise values (?,?,?)",
                exerciseDto.getExercise_id(),
                exerciseDto.getExercise_name(),
                exerciseDto.getDescription()
        );
    }

    public ArrayList<String> getScheduleName() throws SQLException {
        ResultSet rst = CrudUtil.execute("select schedule_id from schedule");

        ArrayList<String> scheduleNames = new ArrayList<>();
        while (rst.next()){
            scheduleNames.add(rst.getString(1));
        }
        return scheduleNames;
    }
}
