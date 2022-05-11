package com.example.app.wallet.repository;

import com.example.app.wallet.model.Wallet;
import com.example.app.wallet.model.enumeration.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN TRUE ELSE FALSE END FROM Wallet w WHERE w.currency = ?1 AND w.customer.id = ?2")
    boolean selectExistsWalletWithSameCurrency(Currency currency, long id);
}
