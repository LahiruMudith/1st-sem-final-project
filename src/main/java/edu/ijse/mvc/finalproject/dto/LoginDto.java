package edu.ijse.mvc.finalproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {
    private String admin_id;
    private String password;
}
