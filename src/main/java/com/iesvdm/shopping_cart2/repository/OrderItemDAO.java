package com.iesvdm.shopping_cart2.repository;

import com.iesvdm.shopping_cart2.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderItemDAO {

    Optional<OrderItem> findById(Long id);

    List<OrderItem> findAll();

    OrderItem create(OrderItem orderItem);

    OrderItem update(OrderItem orderItem);

    void delete(Long id);

    // Sumar line_total por order
    BigDecimal sumLineTotalByOrderId(Long orderId);
}
