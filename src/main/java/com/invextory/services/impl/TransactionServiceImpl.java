package com.invextory.services.impl;

import com.invextory.dtos.TransactionDTO;
import com.invextory.dtos.response.Response;
import com.invextory.exceptions.NotFoundException;
import com.invextory.models.ProductBatch;
import com.invextory.models.Supplier;
import com.invextory.models.Transaction;
import com.invextory.models.User;
import com.invextory.repositories.ProductBatchRepository;
import com.invextory.repositories.SupplierRepository;
import com.invextory.repositories.TransactionRepository;
import com.invextory.repositories.UserRepository;
import com.invextory.services.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.invextory.constants.AppText.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductBatchRepository productBatchRepository;
    private final UserRepository userRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Response createTransaction(TransactionDTO transactionDTO) {
        log.info(LOG_CREATE_TRANSACTION_INIT, transactionDTO.getTransactionType());

        ProductBatch productBatch = productBatchRepository.findById(transactionDTO.getProductBatch().getId())
                .orElseThrow(() -> new NotFoundException(ERROR_PRODUCT_BATCH_NOT_FOUND));

        User user = userRepository.findById(transactionDTO.getUser().getId())
                .orElseThrow(() -> new NotFoundException(ERROR_USER_NOT_FOUND));

        Supplier supplier = supplierRepository.findById(transactionDTO.getSupplier().getId())
                .orElseThrow(() -> new NotFoundException(ERROR_SUPPLIER_NOT_FOUND));

        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        transaction.setProductBatch(productBatch);
        transaction.setUser(user);
        transaction.setSupplier(supplier);

        Transaction savedTransaction = transactionRepository.save(transaction);
        log.info(LOG_CREATE_TRANSACTION_SUCCESS, savedTransaction.getId());

        return Response.builder()
                .status(201)
                .message(TRANSACTION_CREATE_SUCCESS)
                .transaction(modelMapper.map(savedTransaction, TransactionDTO.class))
                .build();
    }

    @Override
    public Response getTransactionById(Long id) {
        log.info(LOG_GET_TRANSACTION_INIT, id);

        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn(ERROR_TRANSACTION_NOT_FOUND);
                    return new NotFoundException(ERROR_TRANSACTION_NOT_FOUND);
                });

        log.info(LOG_GET_TRANSACTION_SUCCESS, id);
        return Response.builder()
                .status(200)
                .message(TRANSACTION_FETCH_SUCCESS)
                .transaction(modelMapper.map(transaction, TransactionDTO.class))
                .build();
    }

    @Override
    public Response getAllTransactions() {
        log.info(LOG_GET_ALL_TRANSACTIONS_INIT);

        List<Transaction> transactions = transactionRepository.findAll();
        List<TransactionDTO> dtos = transactions.stream()
                .map(t -> modelMapper.map(t, TransactionDTO.class))
                .toList();

        log.info(LOG_GET_ALL_TRANSACTIONS_SUCCESS, dtos.size());
        return Response.builder()
                .status(200)
                .message(TRANSACTIONS_FETCH_SUCCESS)
                .transactions(dtos)
                .build();
    }

    @Override
    @Transactional
    public Response updateTransaction(Long id, TransactionDTO transactionDTO) {
        log.info(LOG_UPDATE_TRANSACTION_INIT, id);

        Transaction existing = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ERROR_TRANSACTION_NOT_FOUND));

        modelMapper.map(transactionDTO, existing);
        existing.setUpdatedAt(LocalDateTime.now());

        transactionRepository.save(existing);
        log.info(LOG_UPDATE_TRANSACTION_SUCCESS, id);

        return Response.builder()
                .status(200)
                .message(TRANSACTION_UPDATE_SUCCESS)
                .build();
    }

    @Override
    public Response deleteTransaction(Long id) {
        return null;
    }

}
