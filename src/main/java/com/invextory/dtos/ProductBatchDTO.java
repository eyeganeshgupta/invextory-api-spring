package com.invextory.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.invextory.constants.AppText;
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
public class ProductBatchDTO {

    private Long id;

    @NotBlank(message = AppText.BATCH_NUMBER_REQUIRED)
    @Size(max = 50, message = AppText.BATCH_NUMBER_TOO_LONG)
    private String batchNumber;

    @NotNull(message = AppText.STOCK_QUANTITY_REQUIRED)
    @Min(value = 0, message = AppText.STOCK_QUANTITY_NEGATIVE)
    private Integer stockQuantity;

    private LocalDateTime expiryDate;

    @NotNull(message = AppText.MRP_REQUIRED)
    @Positive(message = AppText.MRP_POSITIVE)
    private BigDecimal mrp;

    @NotNull(message = AppText.PURCHASE_PRICE_REQUIRED)
    @Positive(message = AppText.PURCHASE_PRICE_POSITIVE)
    private BigDecimal purchasePrice;

    @Min(value = 0, message = AppText.DISCOUNT_INVALID)
    private Integer discount;

    @NotNull(message = AppText.GST_REQUIRED)
    @Min(value = 0, message = AppText.GST_INVALID)
    private Integer gstRate;

    private boolean isActive = true;

    @NotNull(message = AppText.SELLING_PRICE_REQUIRED)
    @Positive(message = AppText.SELLING_PRICE_POSITIVE)
    private BigDecimal sellingPrice;

    @NotNull(message = AppText.PRODUCT_ID_REQUIRED)
    private Long productId;

    @NotNull(message = AppText.SUPPLIER_ID_REQUIRED)
    private Long supplierId;

    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getMrp() {
        return mrp;
    }

    public void setMrp(BigDecimal mrp) {
        this.mrp = mrp;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getGstRate() {
        return gstRate;
    }

    public void setGstRate(Integer gstRate) {
        this.gstRate = gstRate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ProductBatchDTO{" +
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
                ", productId=" + productId +
                ", supplierId=" + supplierId +
                ", createdAt=" + createdAt +
                '}';
    }
}
