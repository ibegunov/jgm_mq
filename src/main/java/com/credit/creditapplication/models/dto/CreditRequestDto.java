package com.credit.creditapplication.models.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequestDto {
    private String userId;
    private BigDecimal amount;
    private Integer periodInMonths;
}
