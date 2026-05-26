package org.example.bai03.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bai03.model.dto.request.ProductDTO;
import org.example.bai03.model.entity.Product;
import org.example.bai03.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public Product create(ProductDTO request) {

        Product product = Product.builder()
                .name(request.getName())
                .quantity(request.getQuantity())
                .sku(request.getSku())
                .build();

        log.info("Tạo sản phẩm thành công SKU={}",
                request.getSku());

        return productRepository.save(product);
    }
}