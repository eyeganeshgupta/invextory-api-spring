package com.invextory.repositories;

import com.invextory.models.Product;
import com.invextory.models.ProductBatch;
import com.invextory.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductBatchRepository extends JpaRepository<ProductBatch, Long> {

    Optional<ProductBatch> findByBatchNumber(String batchNumber);

    List<ProductBatch> findByProduct(Product product);

    List<ProductBatch> findBySupplier(Supplier supplier);

    List<ProductBatch> findByIsActiveTrue();

    List<ProductBatch> findByIsActiveFalse();

}
