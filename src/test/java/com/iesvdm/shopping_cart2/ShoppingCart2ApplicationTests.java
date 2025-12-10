package com.iesvdm.shopping_cart2;

import com.iesvdm.shopping_cart2.model.OrderItem;
import com.iesvdm.shopping_cart2.repository.OrderItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShoppingCart2ApplicationTests {

    @Autowired
    private OrderItemRepository orderItemRepository;

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
                System.out.println("Product ID: " + item.getProductId());
                System.out.println("Product Name: " + item.getProductName());
                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Unit Price: " + item.getUnitPrice());
                System.out.println("Line Total: " + item.getLineTotal());
                System.out.println("---");
            });
        }
    }

}
