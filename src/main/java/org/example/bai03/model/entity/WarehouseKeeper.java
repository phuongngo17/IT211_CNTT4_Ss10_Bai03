package org.example.bai03.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "warehouse_keepers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseKeeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String staffCode;
}