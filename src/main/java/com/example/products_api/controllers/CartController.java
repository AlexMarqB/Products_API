package com.example.products_api.controllers;

import com.example.products_api.dtos.CartRecordDto;
import com.example.products_api.middlewares.ObjectExists;
import com.example.products_api.models.CartModel;
import com.example.products_api.respositories.CartRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ObjectExists objectExists;

    @PostMapping("/create")
    public ResponseEntity<CartModel> createCart(@RequestBody @Valid CartRecordDto cartRecordDto) {
        CartModel cartModel = new CartModel();
        BeanUtils.copyProperties(cartRecordDto, cartModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartRepository.save(cartModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCartById(@PathVariable(name = "id")UUID id) {
        if(!objectExists.exists(cartRepository, id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        }
        Optional<CartModel> cart = cartRepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cart);
    }
}


