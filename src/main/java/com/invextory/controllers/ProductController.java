package com.invextory.controllers;

import com.invextory.dtos.response.Response;
import com.invextory.services.ProductService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

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

}
