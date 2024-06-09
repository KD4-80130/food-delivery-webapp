package com.swaad.controller;


import com.swaad.entities.Cart;
import com.swaad.entities.CartItem;
import com.swaad.request.AddCartItemRequest;
import com.swaad.request.UpdateCartItemRequest;
import com.swaad.service.CartService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req, @RequestHeader ("Authorization") String jwt) throws Exception {

        CartItem cartItem = cartService.addItemtToCart(req,jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest req, @RequestHeader ("Authorization") String jwt) throws Exception {

        CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(),req.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }


    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCart(@PathVariable Long id, @RequestHeader ("Authorization") String jwt) throws Exception {

        Cart cart= cartService.removeItemFromCart(id,jwt);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader ("Authorization") String jwt) throws Exception {

        Cart cart= cartService.clearCart(jwt);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


    @GetMapping("/cart/clear")
    public ResponseEntity<Cart> findUserCart(@RequestHeader ("Authorization") String jwt) throws Exception {

        Cart cart= cartService.findCartByUserId(jwt);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }



}
