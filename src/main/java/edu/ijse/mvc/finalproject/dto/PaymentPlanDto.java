package edu.ijse.mvc.finalproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentPlanDto {
    String plan_id;
    String plan_name;
    double price;
}
