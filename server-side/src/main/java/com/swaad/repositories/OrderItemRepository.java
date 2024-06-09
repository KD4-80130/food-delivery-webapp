package com.swaad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swaad.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
