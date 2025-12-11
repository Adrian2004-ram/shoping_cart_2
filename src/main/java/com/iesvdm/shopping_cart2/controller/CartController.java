package com.iesvdm.shopping_cart2.controller;

import com.iesvdm.shopping_cart2.model.OrderItem;
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
    private final AtomicLong nextProductId = new AtomicLong(1);

    //Clases DAO
    @Autowired
    OrderItemDAO orderItemDAO;

    @GetMapping("/step1")
    public String step1get(Model model, @ModelAttribute OrderItemDTO orderItemDTO) {
        model.addAttribute("listProducts", listProducts);
        return "step1"; // devuelve la plantilla step1.html en /templates
    }

    @PostMapping("/items")
    public String addItem(@RequestParam String productName,
                          @RequestParam BigDecimal unitPrice,
                          @RequestParam Integer quantity) {
        // Calculamos el line total
        BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
/*
        orderItemDAO.

        OrderItem item = OrderItem.builder()
                .orderId(orderId)
                .productName(productName)
                .unitPrice(unitPrice)
                .quantity(quantity)
                .lineTotal(lineTotal)
                .build();


        listProducts.add(item);*/
        return "redirect:/cart/step1";
    }

    @GetMapping("/step2")
    public String step2post(@ModelAttribute OrderItemDTO orderItemDTO, Model model) {
        // Pasamos el DTO previamente rellenado en step1 a la vista step2
        model.addAttribute("orderItem", orderItemDTO);
        return "step2";
    }

}
