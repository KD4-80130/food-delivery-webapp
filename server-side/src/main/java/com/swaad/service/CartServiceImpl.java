package com.swaad.service;

import com.swaad.entities.Cart;
import com.swaad.entities.CartItem;
import com.swaad.entities.Food;
import com.swaad.entities.User;
import com.swaad.repositories.CartItemRepository;
import com.swaad.repositories.CartRepository;
import com.swaad.repositories.FoodRepository;
import com.swaad.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private  UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;






    @Override
    public CartItem addItemtToCart(AddCartItemRequest req, String jwt) throws Exception {

        User user =userService.findUserByJwtToken(jwt);

        Food food =foodService.findFoodById(req.getFoodId());

        Cart cart =cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem : cart.getItems()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity()+req.getQuantity();
                return  updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem cartItem = new CartItem();;
        cartItem.setFood(food);
        cartItem.setCart(cart);
        cartItem.setQuantity(req.getQuantity());
        cartItem.setIngredients(req.getIngredient());
        cartItem.setTotalPrice(req.getQuantity()*food.getPrice());

        CartItem savedCartItem =cartItemRepository.save(cartItem);
        return savedCartItem;

    }



    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {

        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("CartItem not found");

        }
        CartItem item = cartItemOptional.get();
        item.setQuantity(quantity);
        //5*100
        item.setTotalPrice(item.getFood().getPrice() *quantity);

        return cartItemRepository.save(item);
    }



    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user =userService.findUserByJwtToken(jwt);

        Cart cart =cartRepository.findByCustomerId(user.getId());
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("CartItem not found");

        }
        CartItem item = cartItemOptional.get();
        cart.getItems().remove(item);

        return cartRepository.save(cart);
    }



    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {
        Long total=0L;

        for(CartItem cartItem : cart.getItems()){
            total+=cartItem.getFood().getPrice()*cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
       Optional<Cart> optionalCart = cartRepository.findById(id);
       if(optionalCart.isEmpty()){
           throw new Exception("Cart is not found with Id :"+id);

       }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(String jwt) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
        return cartRepository.findByCustomerId(user.getId());
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        User user =userService.findUserByJwtToken(jwt);
       Cart cart = findCartByUserId(jwt);

       cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
