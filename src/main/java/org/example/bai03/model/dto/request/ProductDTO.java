package org.example.bai03.model.dto.request;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;

    private Long quantity;

    private String sku;
}