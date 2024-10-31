package edu.ijse.mvc.finalproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportGeneratorDto {
    String report_id;
    String admin_id;
    String type;
}
