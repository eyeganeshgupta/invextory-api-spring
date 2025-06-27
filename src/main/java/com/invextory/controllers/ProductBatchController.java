package com.invextory.controllers;

import com.invextory.dtos.ProductBatchDTO;
import com.invextory.dtos.response.Response;
import com.invextory.services.ProductBatchService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product-batches")
@RequiredArgsConstructor
public class ProductBatchController {

    private final ProductBatchService productBatchService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createBatch(@RequestBody @Valid ProductBatchDTO productBatchDTO) {
        return ResponseEntity.ok(productBatchService.createBatch(productBatchDTO));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Response> getBatchesByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productBatchService.getBatchesByProduct(productId));
    }

    @GetMapping("/{batchId}")
    public ResponseEntity<Response> getBatchById(@PathVariable Long batchId) {
        return ResponseEntity.ok(productBatchService.getBatchById(batchId));
    }

    @PatchMapping("/{batchId}/stock")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<Response> updateBatchStock(
            @PathVariable Long batchId,
            @RequestParam @Min(0) Integer quantity) {
        return ResponseEntity.ok(productBatchService.updateBatchStock(batchId, quantity));
    }

    @DeleteMapping("/{batchId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteBatch(@PathVariable Long batchId) {
        return ResponseEntity.ok(productBatchService.deleteBatch(batchId));
    }

    @PatchMapping("/{batchId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
    public ResponseEntity<Response> updateProductBatch(
            @PathVariable Long batchId,
            @RequestBody @Valid ProductBatchDTO productBatchDTO) {
        return ResponseEntity.ok(productBatchService.updateProductBatch(batchId, productBatchDTO));
    }

}
