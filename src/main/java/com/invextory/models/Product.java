package com.invextory.models;

import com.invextory.constants.AppText;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = AppText.PRODUCT_NAME_REQUIRED)
    @Size(max = 100, message = AppText.PRODUCT_NAME_TOO_LONG)
    private String name;

    @NotBlank(message = AppText.PRODUCT_SKU_REQUIRED)
    @Size(max = 50, message = AppText.PRODUCT_SKU_TOO_LONG)
    private String sku;

    @Positive(message = AppText.PRODUCT_PRICE_POSITIVE)
    private BigDecimal price;

    @Min(value = 0, message = AppText.PRODUCT_STOCK_NEGATIVE)
    private Integer stockQuantity;

    @Size(max = 255, message = AppText.PRODUCT_DESCRIPTION_TOO_LONG)
    private String description;

    private LocalDateTime expiryDate;

    @Size(max = 255, message = AppText.PRODUCT_IMAGE_URL_TOO_LONG)
    private String imageUrl;

    private final LocalDateTime createDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", description='" + (description != null ? description : "N/A") + '\'' +
                ", expiryDate=" + expiryDate +
                ", imageUrl='" + (imageUrl != null ? imageUrl : "N/A") + '\'' +
                ", createDate=" + createDate +
                ", category=" + (category != null ? category.getName() : "N/A") +
                '}';
    }
}
