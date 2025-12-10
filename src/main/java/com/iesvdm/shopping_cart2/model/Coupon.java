package com.iesvdm.shopping_cart2.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    private Long id;
    private String code;
    private String description;
    private String discountType;
    private BigDecimal discountValue;
    private Boolean active;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
}

