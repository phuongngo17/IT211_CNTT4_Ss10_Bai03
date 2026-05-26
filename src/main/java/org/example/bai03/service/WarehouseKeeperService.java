package org.example.bai03.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bai03.model.dto.request.KeeperDTO;
import org.example.bai03.model.entity.WarehouseKeeper;
import org.example.bai03.repository.WarehouseKeeperRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseKeeperService {

    private final WarehouseKeeperRepository keeperRepository;

    public WarehouseKeeper create(KeeperDTO request) {

        WarehouseKeeper keeper = WarehouseKeeper.builder()
                .fullName(request.getFullName())
                .staffCode(request.getStaffCode())
                .build();

        log.info("Tạo nhân viên kho thành công staffCode={}",
                request.getStaffCode());

        return keeperRepository.save(keeper);
    }
}