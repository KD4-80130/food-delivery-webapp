package com.swaad.repositories;

import com.swaad.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart ,Long> {

}
