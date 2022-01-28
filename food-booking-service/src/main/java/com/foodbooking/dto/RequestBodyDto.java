package com.foodbooking.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
public class RequestBodyDto {

    @Size(min = 4, max = 10)
    @NotBlank
    String userId;
    @Min(1)
    @Max(4)
    Long productId;
    @Max(10)
    @PositiveOrZero
    Integer quantity;
    @Positive
    Long accountNumber;
}
