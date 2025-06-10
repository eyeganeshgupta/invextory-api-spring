package com.invextory.services;

import com.invextory.dtos.ProductBatchDTO;
import com.invextory.dtos.response.Response;

public interface ProductBatchService {

    Response createBatch(ProductBatchDTO productBatchDTO);

    Response getBatchesByProduct(Long productId);

    Response updateBatchStock(Long batchId, Integer quantity);

    Response deleteBatch(Long batchId);

    Response getBatchById(Long batchId);

}
