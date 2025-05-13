package com.invextory.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.invextory.constants.AppText;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDTO {

    private Long id;

    @NotBlank(message = AppText.CATEGORY_NAME_REQUIRED)
    @Size(max = 100, message = AppText.CATEGORY_NAME_TOO_LONG)
    private String name;

    private List<ProductDTO> products;

}
