package com.iesvdm.shopping_cart2.dto;

import lombok.*;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Long id;

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

    @NotNull(message = "El total de línea no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El total de línea debe ser mayor a 0")
    @Digits(integer = 10, fraction = 2, message = "El total de línea debe tener máximo 10 dígitos enteros y 2 decimales")
    private BigDecimal lineTotal;

    @NotNull(message = "El ID de la orden no puede ser nulo")
    @Positive(message = "El ID de la orden debe ser un número positivo")
    private Long orderId;

    @NotNull(message = "El ID del producto no puede ser nulo")
    @Positive(message = "El ID del producto debe ser un número positivo")
    private Long productId;
}

