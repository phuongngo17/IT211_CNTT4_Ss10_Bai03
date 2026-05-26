package org.example.bai03.model.dto.request;

import lombok.Data;

@Data
public class InventoryDTO {

    private String sku;

    private Long quantity;

    private Long keeperId;
}