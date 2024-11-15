package edu.ijse.mvc.finalproject.model;

import edu.ijse.mvc.finalproject.db.DBConnection;
import edu.ijse.mvc.finalproject.dto.*;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageMemberModel {
    public String getNextMemberId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select member_id from member order by member_id desc limit 1");

        if (resultSet.next()){
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("M%03d", newIdIndex);
        }
        return null;
    }
    public ArrayList<PaymentPlanDto> getPaymentPlan() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from payment_plan");
        ArrayList<PaymentPlanDto> list = new ArrayList<>();

            while (resultSet.next()){
                PaymentPlanDto paymentDto = new PaymentPlanDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3)
                );
                list.add(paymentDto);
            }
        return list;
    }
    public ArrayList<ExerciseScheduleDto> getSchedule() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from exerciseschedule");
        ArrayList<ExerciseScheduleDto> list = new ArrayList<>();

        while (resultSet.next()){
            ExerciseScheduleDto exerciseScheduleDto = new ExerciseScheduleDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)
            );
            list.add(exerciseScheduleDto);
        }
        return list;
    }
    public ArrayList<DietPlanDto> getDietPlan() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from diet_plan");
        ArrayList<DietPlanDto> list = new ArrayList<>();

        while (resultSet.next()){
            DietPlanDto dietPlanDto = new DietPlanDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            list.add(dietPlanDto);
        }
        return list;
    }
    public ArrayList<MemberDto> getMember() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT" +
                "    m.member_id,\n" +
                "    m.name,\n" +
                "    m.address,\n" +
                "    m.phone_number,\n" +
                "    m.email,\n" +
                "    m.register_date,\n" +
                "    m.weight,\n" +
                "    m.height,\n" +
                "    s.name AS schedule_name,\n" +
                "    pl.plan_name,\n" +
                "    dp.name AS diet_plan_name\n" +
                "FROM\n" +
                "    member m\n" +
                "        LEFT JOIN\n" +
                "    schedule s ON m.schedule_id = s.schedule_id\n" +
                "        LEFT JOIN\n" +
                "    payment_plan pl ON m.plan_id = pl.plan_id\n" +
                "        LEFT JOIN\n" +
                "    diet_plan dp ON m.diet_plan_id = dp.diet_plan_id");
        ArrayList<MemberDto> list = new ArrayList<>();

        while (resultSet.next()){
            MemberDto memberDto = new MemberDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDate(6),
                    resultSet.getDouble(7),
                    resultSet.getDouble(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getString(11)
            );
            list.add(memberDto);
        }
        return list;
    }
    public boolean addMember(MemberDto memberDto) {
        return CrudUtil.execute("insert into member value (?,?,?,?,?,?,?,?,?,?,?)",
                memberDto.getMember_id(),
                memberDto.getName(),
                memberDto.getAddress(),
                memberDto.getPhone_number(),
                memberDto.getEmail(),
                memberDto.getRegister_date(),
                memberDto.getWeight(),
                memberDto.getHeight(),
                memberDto.getSchedule_id(),
                memberDto.getPlan_id(),
                memberDto.getDiet_plan_id()
        );
    }

    public boolean updateMember(MemberDto memberDto) {
        return CrudUtil.execute("update member set name=?, address=?, phone_number=?, email=?, weight=?, height=?, schedule_id=?, plan_id=?, diet_plan_id=? WHERE member_id = ?",
                memberDto.getName(),
                memberDto.getAddress(),
                memberDto.getPhone_number(),
                memberDto.getEmail(),
                memberDto.getWeight(),
                memberDto.getHeight(),
                memberDto.getSchedule_id(),
                memberDto.getPlan_id(),
                memberDto.getDiet_plan_id(),
                memberDto.getMember_id()
                );
    }

    public boolean deleteMember(String id) {
        return CrudUtil.execute("delete from member where member_id = ?",id);
    }
}
