package com.invextory.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.invextory.constants.AppText;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierDTO {

    private Long id;

    @NotBlank(message = AppText.SUPPLIER_NAME_REQUIRED)
    @Size(max = 100, message = AppText.SUPPLIER_NAME_TOO_LONG)
    private String name;

    @Size(max = 150, message = AppText.SUPPLIER_CONTACT_TOO_LONG)
    private String contactInfo;

    @Size(max = 255, message = AppText.SUPPLIER_ADDRESS_TOO_LONG)
    private String address;

    @Override
    public String toString() {
        return "SupplierDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactInfo='" + (contactInfo != null ? contactInfo : "N/A") + '\'' +
                ", address='" + (address != null ? address : "N/A") + '\'' +
                '}';
    }
}
