package com.iesvdm.shopping_cart2.repository;

import com.iesvdm.shopping_cart2.model.CustomerOrder;

import java.util.List;
import java.util.Optional;

public interface CustomerOrderDAO {

    Optional<CustomerOrder> findById(Long id);

    List<CustomerOrder> findAll();

    CustomerOrder create(CustomerOrder customerOrder);

    CustomerOrder update(CustomerOrder customerOrder);

    void delete(Long id);
}

