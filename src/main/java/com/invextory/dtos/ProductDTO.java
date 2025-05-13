package com.invextory.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.invextory.constants.AppText;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    private Long id;

    @NotBlank(message = AppText.PRODUCT_NAME_REQUIRED)
    @Size(max = 100, message = AppText.PRODUCT_NAME_TOO_LONG)
    private String name;

    @NotBlank(message = AppText.PRODUCT_SKU_REQUIRED)
    @Size(max = 50, message = AppText.PRODUCT_SKU_TOO_LONG)
    private String sku;

    @Size(max = 255, message = AppText.PRODUCT_DESCRIPTION_TOO_LONG)
    private String description;

    @Size(max = 255, message = AppText.PRODUCT_IMAGE_URL_TOO_LONG)
    private String imageUrl;

    private LocalDateTime createDate = LocalDateTime.now();

    @NotNull(message = AppText.PRODUCT_CATEGORY_REQUIRED)
    private Long categoryId;

    private Integer numberOfBatches;

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", description='" + (description != null ? description : "N/A") + '\'' +
                ", imageUrl='" + (imageUrl != null ? imageUrl : "N/A") + '\'' +
                ", createDate=" + (createDate != null ? createDate : "N/A") +
                ", categoryId=" + categoryId +
                ", numberOfBatches=" + (numberOfBatches != null ? numberOfBatches : 0) +
                '}';
    }
}
