package edu.ijse.mvc.finalproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExerciseDto {
    private String exercise_id;
    private String exercise_name;
    private String description;
}
