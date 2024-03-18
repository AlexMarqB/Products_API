package com.example.products_api.controllers;

import com.example.products_api.dtos.ItemRecordDto;
import com.example.products_api.middlewares.ObjectExists;
import com.example.products_api.models.ItemModel;
import com.example.products_api.respositories.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ObjectExists objectExists;
    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/create")
    public ResponseEntity<ItemModel> createItem(@RequestBody @Valid ItemRecordDto itemRecordDto) {
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemRecordDto, itemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemRepository.save(itemModel));
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<Object>> getItemsByCartId(@PathVariable(value = "cartId") UUID cartId) {
        Optional<ItemM>
    }
}
