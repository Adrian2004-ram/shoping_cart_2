package com.iesvdm.shopping_cart2;

import com.iesvdm.shopping_cart2.model.CustomerOrder;
import com.iesvdm.shopping_cart2.model.OrderItem;
import com.iesvdm.shopping_cart2.repository.CustomerOrderRepository;
import com.iesvdm.shopping_cart2.repository.OrderItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingCart2ApplicationTests {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testFindAll() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        System.out.println("=== RESULTADOS DE FINDALL ===");
        System.out.println("Total de items: " + orderItems.size());
        System.out.println();

        if (orderItems.isEmpty()) {
            System.out.println("No hay items en la base de datos");
        } else {
            orderItems.forEach(item -> {
                System.out.println("ID: " + item.getId());
                System.out.println("Order ID: " + item.getOrderId());
                System.out.println("Product Name: " + item.getProductName());
                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Unit Price: " + item.getUnitPrice());
                System.out.println("Line Total: " + item.getLineTotal());
                System.out.println("---");
            });
        }
    }

    @Test
    void testFindAllOrderCusutumer() {
        List<CustomerOrder> customerOrders = customerOrderRepository.findAll();
        System.out.println("=== RESULTADOS DE FINDALL CUSTOMER ===");
        System.out.println("Total de orders: " + customerOrders.size());

        if (customerOrders.isEmpty()) {
            System.out.println("No hay orders en la base datos");
        } else {
            customerOrders.forEach(order -> {
                System.out.println("ID: " + order.getId());
                // Usar getters reales del modelo CustomerOrder
                System.out.println("Order Number: " + order.getOrderNumber());
                System.out.println("Created At: " + order.getCreatedAt());
                System.out.println("Status: " + order.getStatus());
                System.out.println("Gross Total: " + order.getGrossTotal());
                System.out.println("Discount Total: " + order.getDiscountTotal());
                System.out.println("Final Total: " + order.getFinalTotal());
                System.out.println("Billing Name: " + order.getBillingName());
                System.out.println("Shipping Name: " + order.getShippingName());
                System.out.println("---");
            });
        }
    }

    @Test
    void testCreateOrderItem_Potato() {
        OrderItem oi = OrderItem.builder()
                .orderId(1L)
                .productName("Potato")
                .unitPrice(new BigDecimal("1.20"))
                .quantity(3)
                .lineTotal(new BigDecimal("3.60"))
                .build();

        OrderItem created = orderItemRepository.create(oi);

        assertNotNull(created, "Created OrderItem should not be null");
        assertNotNull(created.getId(), "Created OrderItem id should not be null");
        assertEquals("Potato", created.getProductName());
        System.out.println("Created OrderItem ID: " + created.getId());
    }

}
