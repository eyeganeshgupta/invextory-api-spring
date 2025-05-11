package com.invextory.models;

import com.invextory.constants.AppText;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_batches")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = AppText.BATCH_NUMBER_REQUIRED)
    @Size(max = 50, message = AppText.BATCH_NUMBER_TOO_LONG)
    @Column(nullable = false, unique = true)
    private String batchNumber;

    @NotNull(message = AppText.STOCK_QUANTITY_REQUIRED)
    @Min(value = 0, message = AppText.STOCK_QUANTITY_NEGATIVE)
    private Integer stockQuantity;

    private LocalDateTime expiryDate;

    @NotNull(message = AppText.MRP_REQUIRED)
    @Positive(message = AppText.MRP_POSITIVE)
    @Column(name = "mrp")
    private BigDecimal mrp;

    @NotNull(message = AppText.PURCHASE_PRICE_REQUIRED)
    @Positive(message = AppText.PURCHASE_PRICE_POSITIVE)
    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    @Min(value = 0, message = AppText.DISCOUNT_INVALID)
    @Column(name = "discount")
    private Integer discount;

    @NotNull(message = AppText.GST_REQUIRED)
    @Min(value = 0, message = AppText.GST_INVALID)
    @Column(name = "gst_rate")
    private Integer gstRate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;

    @NotNull(message = AppText.SELLING_PRICE_REQUIRED)
    @Positive(message = AppText.SELLING_PRICE_POSITIVE)
    @Column(name = "selling_price")
    private BigDecimal sellingPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Override
    public String toString() {
        return "ProductBatch{" +
                "id=" + id +
                ", batchNumber='" + batchNumber + '\'' +
                ", stockQuantity=" + stockQuantity +
                ", expiryDate=" + expiryDate +
                ", mrp=" + mrp +
                ", purchasePrice=" + purchasePrice +
                ", discount=" + discount +
                ", gstRate=" + gstRate +
                ", isActive=" + isActive +
                ", sellingPrice=" + sellingPrice +
                ", product=" + (product != null ? product.getName() : "N/A") +
                ", supplier=" + (supplier != null ? supplier.getName() : "N/A") +
                ", createdAt=" + createdAt +
                '}';
    }
}
