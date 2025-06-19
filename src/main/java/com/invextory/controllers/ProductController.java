package com.invextory.controllers;

import com.invextory.dtos.ProductDTO;
import com.invextory.dtos.response.Response;
import com.invextory.services.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        Response response = productService.createProduct(productDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<Response> getAllProducts() {
        Response response = productService.getAllProducts();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<Response> getProductById(@PathVariable @Min(1) Long id) {
        Response response = productService.getProductById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateProduct(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid ProductDTO productDTO) {
        Response response = productService.updateProduct(id, productDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteProduct(@PathVariable Long id) {
        Response response = productService.deleteProduct(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
