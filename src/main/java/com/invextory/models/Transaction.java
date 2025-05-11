package com.invextory.models;

import com.invextory.constants.AppText;
import com.invextory.enums.TransactionStatus;
import com.invextory.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
@Data
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = AppText.TRANSACTION_TOTAL_PRODUCTS_REQUIRED)
    @Min(value = 1, message = AppText.TRANSACTION_TOTAL_PRODUCTS_REQUIRED)
    private Integer totalProducts;

    @NotNull(message = AppText.TRANSACTION_TOTAL_PRICE_REQUIRED)
    @Positive(message = AppText.TRANSACTION_TOTAL_PRICE_REQUIRED)
    private BigDecimal totalPrice;

    @NotNull(message = AppText.TRANSACTION_TYPE_REQUIRED)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @NotNull(message = AppText.TRANSACTION_STATUS_REQUIRED)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Size(max = 255, message = AppText.TRANSACTION_DESCRIPTION_TOO_LONG)
    private String description;

    @Size(max = 255, message = AppText.TRANSACTION_NOTE_TOO_LONG)
    private String note;

    private final LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", totalProducts=" + totalProducts +
                ", totalPrice=" + totalPrice +
                ", transactionType=" + transactionType +
                ", status=" + status +
                ", description='" + (description != null ? description : "N/A") + '\'' +
                ", note='" + (note != null ? note : "N/A") + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", product=" + (product != null ? product.getName() : "N/A") +
                ", user=" + (user != null ? user.getName() : "N/A") +
                ", supplier=" + (supplier != null ? supplier.getName() : "N/A") +
                '}';
    }
}
