package com.iesvdm.shopping_cart2.repository;

import com.iesvdm.shopping_cart2.model.Coupon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class CouponRepository {

    private final JdbcTemplate jdbcTemplate;

    public CouponRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Coupon> findByCode(String code) {
        String sql = "SELECT * FROM coupon WHERE code = ? AND active = true LIMIT 1";
        return jdbcTemplate.query(sql, new Object[]{code}, rs -> {
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
            return Optional.empty();
        });
    }

    private Coupon mapRow(ResultSet rs) throws java.sql.SQLException {
        return Coupon.builder()
                .id(rs.getLong("id"))
                .code(rs.getString("code"))
                .description(rs.getString("description"))
                .discountType(rs.getString("discount_type"))
                .discountValue(rs.getBigDecimal("discount_value"))
                .active(rs.getBoolean("active"))
                .validFrom(rs.getTimestamp("valid_from") != null ? rs.getTimestamp("valid_from").toLocalDateTime() : null)
                .validTo(rs.getTimestamp("valid_to") != null ? rs.getTimestamp("valid_to").toLocalDateTime() : null)
                .build();
    }
}

