package org.example.bai03.repository;

import org.example.bai03.model.entity.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);

    List<Product> findByQuantityLessThan(Long quantity);

    @Modifying
    @Query("""
        UPDATE Product p
        SET p.quantity = p.quantity - :quantity
        WHERE p.sku = :sku
    """)
    int exportProduct(
            @Param("sku") String sku,
            @Param("quantity") Long quantity
    );

    @Modifying
    @Query("""
        UPDATE Product p
        SET p.quantity = p.quantity + :quantity
        WHERE p.sku = :sku
    """)
    int importProduct(
            @Param("sku") String sku,
            @Param("quantity") Long quantity
    );
}