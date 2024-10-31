package edu.ijse.mvc.finalproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SignInDto {
    private String adminId;
    private String name;
    private String address;
    private String password;
}
