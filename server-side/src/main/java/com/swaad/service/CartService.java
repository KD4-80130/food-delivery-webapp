package com.swaad.service;

import com.swaad.entities.Cart;
import com.swaad.entities.CartItem;
import com.swaad.request.AddCartItemRequest;

public interface CartService {


    public CartItem addItemtToCart(AddCartItemRequest req, String jwt) throws  Exception;

    public  CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws  Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws  Exception;

    public Long calculateCartTotals(Cart cart) throws  Exception;

    public Cart findCartById(Long id) throws  Exception;

    public Cart findCartByUserId(String jwt) throws  Exception;

    public Cart clearCart(String jwt) throws  Exception;


}
