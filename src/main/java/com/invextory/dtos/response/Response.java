package com.invextory.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.invextory.dtos.*;
import com.invextory.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    // Generic Response Info
    private int status;
    private String message;

    // Authentication Details
    private String token;
    private UserRole role;
    private String expirationTime;

    // Pagination Info
    private Integer totalPages;
    private Long totalElements;

    // Data Output Optionals
    private CategoryDTO category;
    private List<CategoryDTO> categories;

    private ProductDTO product;
    private List<ProductDTO> products;

    private ProductBatchDTO productBatch;
    private List<ProductBatchDTO> productBatches;

    private SupplierDTO supplier;
    private List<SupplierDTO> suppliers;

    private TransactionDTO transaction;
    private List<TransactionDTO> transactions;

    private UserDTO user;
    private List<UserDTO> users;

    // Response Timestamp
    private final LocalDateTime timestamp = LocalDateTime.now();

}
