package com.invextory.services.impl;

import com.invextory.dtos.ProductBatchDTO;
import com.invextory.dtos.response.Response;
import com.invextory.exceptions.NotFoundException;
import com.invextory.models.Product;
import com.invextory.models.ProductBatch;
import com.invextory.models.Supplier;
import com.invextory.repositories.ProductBatchRepository;
import com.invextory.repositories.ProductRepository;
import com.invextory.repositories.SupplierRepository;
import com.invextory.services.ProductBatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.invextory.constants.AppText.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductBatchServiceImpl implements ProductBatchService {

    private final ProductBatchRepository batchRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response createBatch(ProductBatchDTO productBatchDTO) {
        log.info(LOG_CREATE_BATCH_INIT, productBatchDTO.getBatchNumber());

        Product product = productRepository.findById(productBatchDTO.getProductId())
                .orElseThrow(() -> new NotFoundException(ERROR_PRODUCT_NOT_FOUND));

        Supplier supplier = supplierRepository.findById(productBatchDTO.getSupplierId())
                .orElseThrow(() -> new NotFoundException(ERROR_SUPPLIER_NOT_FOUND));

        ProductBatch batch = modelMapper.map(productBatchDTO, ProductBatch.class);
        batch.setProduct(product);
        batch.setSupplier(supplier);
        batchRepository.save(batch);

        log.info(LOG_CREATE_BATCH_SUCCESS, batch.getBatchNumber());

        return Response.builder()
                .status(201)
                .message(BATCH_CREATE_SUCCESS_MESSAGE)
                .build();
    }

    @Override
    public Response getBatchesByProduct(Long productId) {
        log.info(LOG_GET_BATCHES_BY_PRODUCT_INIT, productId);

        List<ProductBatch> batches = batchRepository.findByProductId(productId);
        List<ProductBatchDTO> batchDTOs = modelMapper.map(batches, new TypeToken<List<ProductBatchDTO>>() {}.getType());

        log.info(LOG_GET_BATCHES_BY_PRODUCT_SUCCESS, batchDTOs.size(), productId);

        return Response.builder()
                .status(200)
                .message(BATCH_FETCH_SUCCESS_MESSAGE)
                .productBatches(batchDTOs)
                .build();
    }

    @Override
    public Response updateBatchStock(Long batchId, Integer quantity) {
        log.info(LOG_UPDATE_BATCH_STOCK_INIT, batchId);

        ProductBatch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new NotFoundException(ERROR_BATCH_NOT_FOUND));

        batch.setStockQuantity(quantity);
        batchRepository.save(batch);

        log.info(LOG_UPDATE_BATCH_STOCK_SUCCESS, batchId);

        return Response.builder()
                .status(200)
                .message(BATCH_UPDATE_SUCCESS_MESSAGE)
                .build();
    }

    @Override
    public Response deleteBatch(Long batchId) {
        return null;
    }

    @Override
    public Response getBatchById(Long batchId) {
        return null;
    }

}
