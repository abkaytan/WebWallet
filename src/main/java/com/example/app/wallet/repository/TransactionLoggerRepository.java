package com.example.app.wallet.repository;

import com.example.app.wallet.model.WalletServiceTransactionLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//5.1.2 - 22.00
@Repository
public interface TransactionLoggerRepository extends PagingAndSortingRepository<WalletServiceTransactionLogger, Long> {

    @Query("SELECT w FROM WalletServiceTransactionLogger w WHERE w.transactionDateTime= ?1")
    Page<List<WalletServiceTransactionLogger>> findAllTransactionByTransactionDate(LocalDate transactionDate, Pageable pageable);
}
