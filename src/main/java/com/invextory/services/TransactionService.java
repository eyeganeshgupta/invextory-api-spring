package com.invextory.services;

import com.invextory.dtos.TransactionDTO;
import com.invextory.dtos.response.Response;

public interface TransactionService {

    Response createTransaction(TransactionDTO transactionDTO);

    Response getTransactionById(Long id);

    Response getAllTransactions();

    Response updateTransaction(Long id, TransactionDTO transactionDTO);

    Response deleteTransaction(Long id);

}
