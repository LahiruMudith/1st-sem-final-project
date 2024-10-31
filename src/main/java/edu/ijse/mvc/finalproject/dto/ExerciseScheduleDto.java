package edu.ijse.mvc.finalproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExerciseScheduleDto {
    private String schedule_id;
    private String exercise_id;
    private String exercise_name;
    private String exercise_set;
}
