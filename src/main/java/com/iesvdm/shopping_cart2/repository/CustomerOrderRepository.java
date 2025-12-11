package com.iesvdm.shopping_cart2.repository;

import com.iesvdm.shopping_cart2.model.CustomerOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerOrderRepository implements CustomerOrderDAO {

    private final JdbcTemplate jdbcTemplate;

    public CustomerOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<CustomerOrder> findById(Long id) {
        String sql = "SELECT * FROM customer_order WHERE id = ?";
        CustomerOrder order = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> CustomerOrder.builder()
                .id(rs.getLong("id"))
                .orderNumber(rs.getString("order_number"))
                .createdAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                .status(rs.getString("status"))
                .grossTotal(rs.getBigDecimal("gross_total"))
                .discountTotal(rs.getBigDecimal("discount_total"))
                .finalTotal(rs.getBigDecimal("final_total"))
                .couponId(rs.getLong("coupon_id"))
                .paymentMethod(rs.getString("payment_method"))
                .paymentStatus(rs.getString("payment_status"))
                .paymentCountry(rs.getString("payment_country"))
                .billingName(rs.getString("billing_name"))
                .billingTaxId(rs.getString("billing_tax_id"))
                .billingStreet(rs.getString("billing_street"))
                .billingCity(rs.getString("billing_city"))
                .billingPostalCode(rs.getString("billing_postal_code"))
                .billingCountry(rs.getString("billing_country"))
                .shippingName(rs.getString("shipping_name"))
                .shippingStreet(rs.getString("shipping_street"))
                .shippingCity(rs.getString("shipping_city"))
                .shippingPostalCode(rs.getString("shipping_postal_code"))
                .shippingCountry(rs.getString("shipping_country"))
                .build(), id);
        return Optional.ofNullable(order);
    }

    @Override
    public List<CustomerOrder> findAll() {
        String sql = "SELECT * FROM customer_order";
        return jdbcTemplate.query(sql, (rs, rowNum) -> CustomerOrder.builder()
                .id(rs.getLong("id"))
                .orderNumber(rs.getString("order_number"))
                .createdAt(rs.getTimestamp("created_at") != null ? rs.getTimestamp("created_at").toLocalDateTime() : null)
                .status(rs.getString("status"))
                .grossTotal(rs.getBigDecimal("gross_total"))
                .discountTotal(rs.getBigDecimal("discount_total"))
                .finalTotal(rs.getBigDecimal("final_total"))
                .couponId(rs.getLong("coupon_id"))
                .paymentMethod(rs.getString("payment_method"))
                .paymentStatus(rs.getString("payment_status"))
                .paymentCountry(rs.getString("payment_country"))
                .billingName(rs.getString("billing_name"))
                .billingTaxId(rs.getString("billing_tax_id"))
                .billingStreet(rs.getString("billing_street"))
                .billingCity(rs.getString("billing_city"))
                .billingPostalCode(rs.getString("billing_postal_code"))
                .billingCountry(rs.getString("billing_country"))
                .shippingName(rs.getString("shipping_name"))
                .shippingStreet(rs.getString("shipping_street"))
                .shippingCity(rs.getString("shipping_city"))
                .shippingPostalCode(rs.getString("shipping_postal_code"))
                .shippingCountry(rs.getString("shipping_country"))
                .build());
    }

    @Override
    public CustomerOrder create(CustomerOrder customerOrder) {
        // Establecer valores por defecto si faltan
        if (customerOrder.getOrderNumber() == null || customerOrder.getOrderNumber().isBlank()) {
            customerOrder.setOrderNumber("ORD-" + System.currentTimeMillis());
        }
        if (customerOrder.getCreatedAt() == null) {
            customerOrder.setCreatedAt(LocalDateTime.now());
        }
        if (customerOrder.getStatus() == null) {
            customerOrder.setStatus("PENDING");
        }
        if (customerOrder.getGrossTotal() == null) {
            customerOrder.setGrossTotal(BigDecimal.ZERO);
        }
        if (customerOrder.getDiscountTotal() == null) {
            customerOrder.setDiscountTotal(BigDecimal.ZERO);
        }
        if (customerOrder.getFinalTotal() == null) {
            customerOrder.setFinalTotal(customerOrder.getGrossTotal().subtract(customerOrder.getDiscountTotal()));
        }
        if (customerOrder.getPaymentMethod() == null) {
            customerOrder.setPaymentMethod("CREDIT_CARD");
        }
        if (customerOrder.getPaymentStatus() == null) {
            customerOrder.setPaymentStatus("PENDING");
        }
        if (customerOrder.getBillingName() == null) {
            customerOrder.setBillingName("Unknown");
        }
        if (customerOrder.getShippingName() == null) {
            customerOrder.setShippingName(customerOrder.getBillingName());
        }

        String sql = "INSERT INTO customer_order (order_number, created_at, status, gross_total, discount_total, final_total, coupon_id, payment_method, payment_status, payment_country, billing_name, billing_tax_id, billing_street, billing_city, billing_postal_code, billing_country, shipping_name, shipping_street, shipping_city, shipping_postal_code, shipping_country) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customerOrder.getOrderNumber());
            ps.setTimestamp(2, Timestamp.valueOf(customerOrder.getCreatedAt()));
            ps.setString(3, customerOrder.getStatus());
            ps.setBigDecimal(4, customerOrder.getGrossTotal());
            ps.setBigDecimal(5, customerOrder.getDiscountTotal());
            ps.setBigDecimal(6, customerOrder.getFinalTotal());
            if (customerOrder.getCouponId() != null) ps.setLong(7, customerOrder.getCouponId()); else ps.setNull(7, java.sql.Types.BIGINT);
            ps.setString(8, customerOrder.getPaymentMethod());
            ps.setString(9, customerOrder.getPaymentStatus());
            ps.setString(10, customerOrder.getPaymentCountry());
            ps.setString(11, customerOrder.getBillingName());
            ps.setString(12, customerOrder.getBillingTaxId());
            ps.setString(13, customerOrder.getBillingStreet());
            ps.setString(14, customerOrder.getBillingCity());
            ps.setString(15, customerOrder.getBillingPostalCode());
            ps.setString(16, customerOrder.getBillingCountry());
            ps.setString(17, customerOrder.getShippingName());
            ps.setString(18, customerOrder.getShippingStreet());
            ps.setString(19, customerOrder.getShippingCity());
            ps.setString(20, customerOrder.getShippingPostalCode());
            ps.setString(21, customerOrder.getShippingCountry());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) customerOrder.setId(key.longValue());
        return customerOrder;
    }

    @Override
    public CustomerOrder update(CustomerOrder customerOrder) {
        String sql = "UPDATE customer_order SET status = ?, gross_total = ?, discount_total = ?, final_total = ?, coupon_id = ?, payment_method = ?, payment_status = ?, payment_country = ?, billing_name = ?, billing_tax_id = ?, billing_street = ?, billing_city = ?, billing_postal_code = ?, billing_country = ?, shipping_name = ?, shipping_street = ?, shipping_city = ?, shipping_postal_code = ?, shipping_country = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                customerOrder.getStatus(),
                customerOrder.getGrossTotal(),
                customerOrder.getDiscountTotal(),
                customerOrder.getFinalTotal(),
                customerOrder.getCouponId(),
                customerOrder.getPaymentMethod(),
                customerOrder.getPaymentStatus(),
                customerOrder.getPaymentCountry(),
                customerOrder.getBillingName(),
                customerOrder.getBillingTaxId(),
                customerOrder.getBillingStreet(),
                customerOrder.getBillingCity(),
                customerOrder.getBillingPostalCode(),
                customerOrder.getBillingCountry(),
                customerOrder.getShippingName(),
                customerOrder.getShippingStreet(),
                customerOrder.getShippingCity(),
                customerOrder.getShippingPostalCode(),
                customerOrder.getShippingCountry(),
                customerOrder.getId());
        return customerOrder;
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM customer_order WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
