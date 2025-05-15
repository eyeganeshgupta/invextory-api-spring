package com.invextory.repositories;

import com.invextory.enums.TransactionStatus;
import com.invextory.enums.TransactionType;
import com.invextory.models.ProductBatch;
import com.invextory.models.Supplier;
import com.invextory.models.Transaction;
import com.invextory.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

    List<Transaction> findByUser(User user);

    List<Transaction> findBySupplier(Supplier supplier);

    List<Transaction> findByProductBatch(ProductBatch productBatch);

    List<Transaction> findByTransactionType(TransactionType transactionType);

    List<Transaction> findByStatus(TransactionStatus status);

    List<Transaction> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}
