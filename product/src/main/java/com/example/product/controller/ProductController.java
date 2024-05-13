package com.example.product.controller;

import com.example.product.service.ProductService;


import com.example.shoppingclient.dtoProduct.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<com.example.shoppingclient.dtoProduct.ProductDTO> getProducts() {
        return service.getAll();
    }

    @GetMapping("/category/{id}")
    public List<ProductDTO> getProductByCategory(@PathVariable final Long id) {
        return service.getProductByCategoryId(id);
    }

    @GetMapping("{productIdentifier}")
    public ProductDTO findByIdentifier(@PathVariable final String productIdentifier) {
        return service.findByProductIdentifier(productIdentifier);
    }

    @PostMapping
    public ProductDTO newProduct(@Valid @RequestBody ProductDTO dto) {
        return service.save(dto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable final long id) {
        service.delete(id);
    }

}

