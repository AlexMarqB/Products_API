package com.example.products_api.controllers;

import com.example.products_api.dtos.ProductRecordDto;
import com.example.products_api.middlewares.ObjectExists;
import com.example.products_api.models.ProductModel;
import com.example.products_api.respositories.ProductRepository;
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
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ObjectExists objectExists;

    // cria automaticamente o "constructor"
    @Autowired
    ProductRepository productRepository;

    // metódo post para criar products
    @PostMapping("/create")
    public ResponseEntity<ProductModel> createProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        // iniciamos o productModel
        ProductModel productModel = new ProductModel();
        // Converte o DTO para o Model
        BeanUtils.copyProperties(productRecordDto, productModel);
        //metódo JPA save == create
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    //get all
    @GetMapping("/all")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        //metódo JPA findAll
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    //get by id
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable(value = "id") UUID id) {


        if(!objectExists.exists(productRepository, id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        //metódo JPA findById
        Optional<ProductModel> product = productRepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
        @RequestBody @Valid ProductRecordDto productRecordDto) {

        if(!objectExists.exists(productRepository, id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        //tentamos pegar o objeto com id respectivo
        Optional<ProductModel> product = productRepository.findById(id); //tentamos encontrar o objeto na tabela
        ProductModel productModel = product.get();
        // passa os atributos vindos do Body para o objeto já existente que pegamos no db
        BeanUtils.copyProperties(productRecordDto, productModel);
        // substituimos o objeto antigo pelo novo com novos atributos
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        if(!objectExists.exists(productRepository, id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        Optional<ProductModel> product = productRepository.findById(id);
        productRepository.delete(product.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully.");
    }
}
