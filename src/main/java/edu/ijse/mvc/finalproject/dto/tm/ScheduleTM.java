package edu.ijse.mvc.finalproject.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ScheduleTM {
    String schedule_id;
    String name;
    String admin_id;
    String exercise_id;
    String exercise_name;
    int exercise_count;
    int exercise_set;
}
