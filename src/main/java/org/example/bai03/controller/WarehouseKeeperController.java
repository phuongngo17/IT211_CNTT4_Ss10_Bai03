package org.example.bai03.controller;

import lombok.RequiredArgsConstructor;
import org.example.bai03.model.dto.request.KeeperDTO;
import org.example.bai03.model.entity.WarehouseKeeper;
import org.example.bai03.service.WarehouseKeeperService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/keepers")
@RequiredArgsConstructor
public class WarehouseKeeperController {

    private final WarehouseKeeperService keeperService;

    @PostMapping
    public WarehouseKeeper create(@RequestBody KeeperDTO request) {

        return keeperService.create(request);
    }
}
