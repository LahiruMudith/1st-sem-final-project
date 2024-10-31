package edu.ijse.mvc.finalproject.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentDto {
    private String payment_id;
    private Date payment_date;
    private String admin_id;
}
