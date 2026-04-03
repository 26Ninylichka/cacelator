package com.example.cacelator.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product_package_price")
@Getter
@Setter
public class ProductPackagePriceEntity {

    @Id
    @Column(name = "package_price_id")
    private UUID packagePriceId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "package_qty", nullable = false)
    private BigDecimal packageQty;

    @Column(name = "package_price", nullable = false)
    private BigDecimal packagePrice;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "valid_from")
    private LocalDateTime validFrom;

    @Column(name = "valid_to")
    private LocalDateTime validTo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}