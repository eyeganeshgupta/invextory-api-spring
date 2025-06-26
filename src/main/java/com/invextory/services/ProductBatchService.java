package com.invextory.services;

import com.invextory.dtos.ProductBatchDTO;
import com.invextory.dtos.response.Response;
import jakarta.transaction.Transactional;

public interface ProductBatchService {

    Response createBatch(ProductBatchDTO productBatchDTO);

    Response getBatchesByProduct(Long productId);

    Response updateBatchStock(Long batchId, Integer quantity);

    @Transactional
    Response updateProductBatch(Long id, ProductBatchDTO productBatchDTO);

    Response deleteBatch(Long batchId);

    Response getBatchById(Long batchId);

}
