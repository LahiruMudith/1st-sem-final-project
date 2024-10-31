package edu.ijse.mvc.finalproject.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeSalaryDto {
    private String salary_id;
    private Date date;
    private double amount;
    private String admin_id;
    private String employee_id;
}
