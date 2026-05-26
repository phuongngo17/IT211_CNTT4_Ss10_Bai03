package org.example.bai03.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bai03.model.dto.request.InventoryDTO;
import org.example.bai03.model.entity.Product;
import org.example.bai03.repository.ProductRepository;
import org.example.bai03.repository.WarehouseKeeperRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final ProductRepository productRepository;
    private final WarehouseKeeperRepository keeperRepository;

    @Transactional
    public String importInventory(InventoryDTO request) {

        keeperRepository.findById(request.getKeeperId())
                .orElseThrow(() -> {
                    log.warn("Không tìm thấy keeper id={}",
                            request.getKeeperId());

                    return new RuntimeException("Keeper không tồn tại");
                });

        Product product = productRepository.findBySku(request.getSku())
                .orElseThrow(() -> {
                    log.warn("Không tìm thấy SKU={}",
                            request.getSku());

                    return new RuntimeException("SKU không tồn tại");
                });

        productRepository.importProduct(
                request.getSku(),
                request.getQuantity()
        );

        log.info("Nhập kho thành công SKU={} quantity={}",
                request.getSku(),
                request.getQuantity());

        return "Import thành công";
    }

    @Transactional
    public String exportInventory(InventoryDTO request) {

        try {

            keeperRepository.findById(request.getKeeperId())
                    .orElseThrow(() -> {
                        log.warn("Không tìm thấy keeper id={}",
                                request.getKeeperId());

                        return new RuntimeException("Keeper không tồn tại");
                    });

            Product product = productRepository.findBySku(request.getSku())
                    .orElseThrow(() -> {
                        log.warn("Không tìm thấy SKU={}",
                                request.getSku());

                        return new RuntimeException("SKU không tồn tại");
                    });

            if (product.getQuantity() < request.getQuantity()) {

                log.warn("Xuất kho vượt tồn kho SKU={}",
                        request.getSku());

                throw new IllegalArgumentException(
                        "Số lượng xuất hàng vượt quá tồn kho hiện tại!"
                );
            }

            productRepository.exportProduct(
                    request.getSku(),
                    request.getQuantity()
            );

            log.info("Xuất kho thành công SKU={} quantity={}",
                    request.getSku(),
                    request.getQuantity());

            return "Export thành công";

        } catch (DataAccessException e) {

            log.error("Lỗi Database khi export kho", e);

            throw e;
        }
    }

    public List<Product> lowStock() {

        return productRepository.findByQuantityLessThan(5L);
    }
}