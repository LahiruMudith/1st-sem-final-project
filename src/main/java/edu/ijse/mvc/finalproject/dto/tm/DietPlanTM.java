package edu.ijse.mvc.finalproject.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DietPlanTM {
    private String diet_plan_id;
    private String admin_id;
    private String name;
    private String duration;
    private String description;
}
