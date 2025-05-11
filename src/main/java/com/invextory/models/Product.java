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
import java.util.List;

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

    @Size(max = 255, message = AppText.PRODUCT_DESCRIPTION_TOO_LONG)
    private String description;

    private LocalDateTime expiryDate;

    @Size(max = 255, message = AppText.PRODUCT_IMAGE_URL_TOO_LONG)
    private String imageUrl;

    private final LocalDateTime createDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductBatch> batches;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", description='" + (description != null ? description : "N/A") + '\'' +
                ", expiryDate=" + expiryDate +
                ", imageUrl='" + (imageUrl != null ? imageUrl : "N/A") + '\'' +
                ", createDate=" + createDate +
                ", category=" + (category != null ? category.getName() : "N/A") +
                ", numberOfBatches=" + (batches != null ? batches.size() : 0) +
                '}';
    }
}
