package com.iesvdm.shopping_cart2.dto;

import lombok.*;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {


    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Size(min = 3, max = 255, message = "El nombre del producto debe tener entre 3 y 255 caracteres")
    private String productName;

    @NotNull(message = "El precio unitario no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio unitario debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El precio unitario debe tener máximo 10 dígitos enteros y 2 decimales")
    private BigDecimal unitPrice;

    @NotNull(message = "La cantidad no puede ser nula")
    @Min(value = 1, message = "La cantidad debe ser mínimo 1")
    @Max(value = 999, message = "La cantidad no puede exceder 999 unidades")
    private Integer quantity;

}

