package org.example.bai03.controller;

import lombok.RequiredArgsConstructor;
import org.example.bai03.model.dto.request.InventoryDTO;
import org.example.bai03.model.entity.Product;
import org.example.bai03.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/import")
    public String importInventory(
            @RequestBody InventoryDTO request
    ) {

        return inventoryService.importInventory(request);
    }

    @PostMapping("/export")
    public String exportInventory(
            @RequestBody InventoryDTO request
    ) {

        return inventoryService.exportInventory(request);
    }

    @GetMapping("/low-stock")
    public List<Product> lowStock() {

        return inventoryService.lowStock();
    }
}
