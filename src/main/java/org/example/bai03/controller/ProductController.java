package org.example.bai03.controller;

import lombok.RequiredArgsConstructor;
import org.example.bai03.model.dto.request.ProductDTO;
import org.example.bai03.model.entity.Product;
import org.example.bai03.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product create(@RequestBody ProductDTO request) {

        return productService.create(request);
    }
}