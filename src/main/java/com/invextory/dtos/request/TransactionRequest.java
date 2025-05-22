package com.invextory.dtos.request;

import com.invextory.constants.AppText;
import jakarta.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @NotNull(message = AppText.PRODUCT_BATCH_ID_REQUIRED)
    private Long productBatchId;

    @NotNull(message = AppText.SUPPLIER_ID_REQUIRED)
    private Long supplierId;

    @NotNull(message = AppText.TRANSACTION_QUANTITY_REQUIRED)
    @Min(value = 1, message = AppText.TRANSACTION_QUANTITY_POSITIVE)
    private Integer quantity;

    @Size(max = 255, message = AppText.TRANSACTION_DESCRIPTION_TOO_LONG)
    private String description;

    @Size(max = 255, message = AppText.TRANSACTION_NOTE_TOO_LONG)
    private String note;
}
