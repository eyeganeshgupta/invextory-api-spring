package com.invextory.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.invextory.constants.AppText;
import com.invextory.enums.TransactionStatus;
import com.invextory.enums.TransactionType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {

    private Long id;

    @NotNull(message = AppText.TRANSACTION_TOTAL_PRODUCTS_REQUIRED)
    @Min(value = 1, message = AppText.TRANSACTION_TOTAL_PRODUCTS_REQUIRED)
    private Integer totalProducts;

    @NotNull(message = AppText.TRANSACTION_TOTAL_PRICE_REQUIRED)
    @Positive(message = AppText.TRANSACTION_TOTAL_PRICE_REQUIRED)
    private BigDecimal totalPrice;

    @NotNull(message = AppText.TRANSACTION_TYPE_REQUIRED)
    private TransactionType transactionType;

    @NotNull(message = AppText.TRANSACTION_STATUS_REQUIRED)
    private TransactionStatus status;

    @Size(max = 255, message = AppText.TRANSACTION_DESCRIPTION_TOO_LONG)
    private String description;

    @Size(max = 255, message = AppText.TRANSACTION_NOTE_TOO_LONG)
    private String note;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    @NotNull(message = AppText.BATCH_NUMBER_REQUIRED)
    private ProductBatchDTO productBatch;

    @NotNull(message = AppText.USER_ID_REQUIRED)
    private UserDTO user;

    @NotNull(message = AppText.SUPPLIER_ID_REQUIRED)
    private SupplierDTO supplier;

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", totalProducts=" + totalProducts +
                ", totalPrice=" + totalPrice +
                ", transactionType=" + transactionType +
                ", status=" + status +
                ", description='" + (description != null ? description : "N/A") + '\'' +
                ", note='" + (note != null ? note : "N/A") + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", productBatch=" + (productBatch != null ? productBatch.getBatchNumber() : "N/A") +
                ", user=" + (user != null ? user.getName() : "N/A") +
                ", supplier=" + (supplier != null ? supplier.getName() : "N/A") +
                '}';
    }

}
