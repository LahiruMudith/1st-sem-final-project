package edu.ijse.mvc.finalproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDto {
    private String admin_id;
    private String name;
    private String address;
    private String password;
}
