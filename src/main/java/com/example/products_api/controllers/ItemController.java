package com.example.products_api.controllers;

import com.example.products_api.dtos.ItemRecordDto;
import com.example.products_api.middlewares.ObjectExists;
import com.example.products_api.models.CartModel;
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
        CartModel cart = 
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemRecordDto, itemModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemRepository.save(itemModel));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<ItemModel>> getItemsByCartId(@PathVariable(value = "cartId") UUID cartId) {
        return ResponseEntity.status(HttpStatus.OK).body(itemRepository.findAllByCartId(cartId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getItemById(@PathVariable(value = "id") UUID id) {
        if(!objectExists.exists(itemRepository, id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
        Optional<ItemModel> item = itemRepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @PutMapping("/update/{cartId}/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable(value = "id") UUID id, @PathVariable(value = "cartId") UUID cartId, @RequestBody int quantity) {
        if(!objectExists.exists(itemRepository, id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
        ItemModel item = itemRepository.findByIdAndCartId(id, cartId);
        item.setQuantity(quantity);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

}
