package com.swaad.repositories;

import com.swaad.entities.Cart;
import com.swaad.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
