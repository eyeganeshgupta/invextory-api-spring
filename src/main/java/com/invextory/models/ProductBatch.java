package com.invextory.models;

import com.invextory.constants.AppText;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
                ", product=" + (product != null ? product.getName() : "N/A") +
                ", supplier=" + (supplier != null ? supplier.getName() : "N/A") +
                ", createdAt=" + createdAt +
                '}';
    }
}
