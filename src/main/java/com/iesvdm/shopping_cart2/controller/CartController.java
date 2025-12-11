package com.iesvdm.shopping_cart2.controller;

import com.iesvdm.shopping_cart2.model.CustomerOrder;
import com.iesvdm.shopping_cart2.model.OrderItem;
import com.iesvdm.shopping_cart2.repository.CustomerOrderDAO;
import com.iesvdm.shopping_cart2.repository.OrderItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesvdm.shopping_cart2.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/cart")
public class CartController {

    // Lista en memoria para almacenar los productos añadidos en el paso 1
    private final List<OrderItemDTO> listProducts = new ArrayList<>();
    private Long currentOrderId = null;

    //Clases DAO
    @Autowired
    OrderItemDAO orderItemDAO;

    @Autowired
    CustomerOrderDAO customerOrderDAO;

    @GetMapping("/step1")
    public String step1get(Model model, @ModelAttribute OrderItemDTO orderItemDTO) {
        model.addAttribute("listProducts", listProducts);
        return "step1"; // devuelve la plantilla step1.html en /templates
    }

    @PostMapping("/items")
    public String addItem(@RequestParam String productName,
                          @RequestParam BigDecimal unitPrice,
                          @RequestParam Integer quantity) {

        // Crear CustomerOrder por defecto si aún no existe
        if (currentOrderId == null) {
            CustomerOrder order = CustomerOrder.builder().build();
            CustomerOrder createdOrder = customerOrderDAO.create(order);
            currentOrderId = createdOrder.getId();
        }

        // Calculamos el line total
        BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));

        // Construimos el OrderItem y lo persistimos
        OrderItem item = OrderItem.builder()
                .orderId(currentOrderId)
                .productName(productName)
                .unitPrice(unitPrice)
                .quantity(quantity)
                .lineTotal(lineTotal)
                .build();

        OrderItem created = orderItemDAO.create(item);

        // Añadimos representación DTO a la lista en memoria para mostrar en la vista
        OrderItemDTO dto = OrderItemDTO.builder()
                .productName(created.getProductName())
                .unitPrice(created.getUnitPrice())
                .quantity(created.getQuantity())
                .build();

        listProducts.add(dto);

        return "redirect:/cart/step1";
    }

    @GetMapping("/step2")
    public String step2post(@ModelAttribute OrderItemDTO orderItemDTO, Model model) {
        // Pasamos el DTO previamente rellenado en step1 a la vista step2
        model.addAttribute("orderItem", orderItemDTO);
        return "step2";
    }

}
