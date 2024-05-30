package com.swaad.controller;


import com.swaad.config.JwtProvider;
import com.swaad.entities.Cart;
import com.swaad.entities.USER_ROLE;
import com.swaad.entities.User;
import com.swaad.repositories.CartRepository;
import com.swaad.repositories.UserRepository;
import com.swaad.request.LoginRequest;
import com.swaad.response.AuthResponse;
import com.swaad.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {
        User isEmailExist =userRepository.findByEmail(user.getEmail());
        if(isEmailExist != null){
            throw new Exception("Email is already used by another account");
        }

        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(createdUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register Success");
        authResponse.setRole(savedUser.getRole());

        return  new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req){
        String username = req.getEmail();
        String password = req.getPassword();

        Authentication authentication = authenticate(username,password);

        Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
        String role = authorities.isEmpty()? null:authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register Success");
        authResponse.setRole(USER_ROLE.valueOf(role));
//        authResponse.setRole(savedUser.getRole());

        return  new ResponseEntity<>(authResponse, HttpStatus.OK);

    }


    public Authentication authenticate(String username, String password){
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

        if (userDetails==null){
            throw new BadCredentialsException("Invalid username......");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password....");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }




}