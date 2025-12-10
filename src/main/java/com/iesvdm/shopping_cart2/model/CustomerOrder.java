package com.iesvdm.shopping_cart2.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {
    private Long id;
    private String orderNumber;
    private LocalDateTime createdAt;
    private String status;
    private BigDecimal grossTotal;
    private BigDecimal discountTotal;
    private BigDecimal finalTotal;
    private Long couponId;
    private String paymentMethod;
    private String paymentStatus;
    private String paymentCountry;
    private String billingName;
    private String billingTaxId;
    private String billingStreet;
    private String billingCity;
    private String billingPostalCode;
    private String billingCountry;
    private String shippingName;
    private String shippingStreet;
    private String shippingCity;
    private String shippingPostalCode;
    private String shippingCountry;
}

