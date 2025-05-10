package com.invextory.models;

import com.invextory.constants.AppText;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "suppliers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = AppText.SUPPLIER_NAME_REQUIRED)
    @Size(max = 100, message = AppText.SUPPLIER_NAME_TOO_LONG)
    @Column(nullable = false, length = 100)
    private String name;

    @Size(max = 150, message = AppText.SUPPLIER_CONTACT_TOO_LONG)
    @Column(name = "contact_info", length = 150)
    private String contactInfo;

    @Size(max = 255, message = AppText.SUPPLIER_ADDRESS_TOO_LONG)
    @Column(length = 255)
    private String address;

    @Override
    public String toString() {
        return "Supplier{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
