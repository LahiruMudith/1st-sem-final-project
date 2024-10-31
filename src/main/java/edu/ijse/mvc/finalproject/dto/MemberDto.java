package edu.ijse.mvc.finalproject.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {
    private String member_id;
    private String name;
    private String address;
    private String phone_number;
    private String email;
    private Date register_date;
    private Date year_of_birth;
    private double weight;
    private double height;
    private String schedule_id;
    private String plan_id;
    private String diet_plan_id;
}
