package com.iesvdm.shopping_cart2.repository;

import com.iesvdm.shopping_cart2.model.OrderItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
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
                        .productName(rs.getString("product_name"))
                        .quantity(rs.getInt("quantity"))
                        .unitPrice(rs.getBigDecimal("unit_price"))
                        .lineTotal(rs.getBigDecimal("line_total"))
                        .build());
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        String sql = "INSERT INTO order_item (order_id, product_name, quantity, unit_price) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, orderItem.getOrderId() == null ? 0L : orderItem.getOrderId());
            ps.setString(2, orderItem.getProductName());
            ps.setInt(3, orderItem.getQuantity());
            ps.setBigDecimal(4, orderItem.getUnitPrice());
            return ps;
        }, keyHolder);

       // calculate lineTotal
        BigDecimal lineTotal = orderItem.getUnitPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
        orderItem.setLineTotal(lineTotal);

        Number key = keyHolder.getKey();
        if (key != null) {
            orderItem.setId(key.longValue());
        }

        return orderItem;
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
