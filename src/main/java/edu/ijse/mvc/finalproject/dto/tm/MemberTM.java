package edu.ijse.mvc.finalproject.dto.tm;

import lombok.*;

import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberTM {
    private String member_id;
    private String name;
    private String address;
    private String phone_number;
    private String email;
    private Date register_date;
    private double weight;
    private double height;
    private String schedule_name;
    private String plan_name;
    private String diet_plan_name;
}
