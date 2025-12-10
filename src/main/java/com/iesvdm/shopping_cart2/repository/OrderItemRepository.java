package com.iesvdm.shopping_cart2.repository;

import com.iesvdm.shopping_cart2.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderItemRepository implements OrderItemDAO {

    private final JdbcTemplate jdbcTemplate;

    public OrderItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<OrderItem> findAll() {
        String sql = "SELECT * FROM order_item";
        return jdbcTemplate.query(
                sql,
                (rs, _) -> OrderItem.builder()
                        .id(rs.getLong("id"))
                        .orderId(rs.getLong("order_id"))
                        .productId(rs.getLong("product_id"))
                        .productName(rs.getString("product_name"))
                        .quantity(rs.getInt("quantity"))
                        .unitPrice(rs.getBigDecimal("unit_price"))
                        .lineTotal(rs.getBigDecimal("line_total"))
                        .build());
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        return null;
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}

