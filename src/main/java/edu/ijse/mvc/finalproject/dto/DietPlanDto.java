package edu.ijse.mvc.finalproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DietPlanDto {
    private String diet_plan_id;
    private String admin_id;
    private String name;
    private String duration;
    private String description;
}
