package edu.ijse.mvc.finalproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FitnessCenterDto {
    private String center_id;
    private String admin_id;
    private String center_name;
    private String location;
}
