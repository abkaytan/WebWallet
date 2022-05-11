package com.example.app.wallet.model;


import com.example.app.wallet.model.enumeration.Currency;
import com.example.app.wallet.model.enumeration.TransationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WalletServiceTransactionLogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private double balanceBefore;
    private double balanceAfter;
    private double transactionAmount;

    @Enumerated(EnumType.STRING) //enumları sayı olarak eklemek yerine string olarak eklemek için enumtype değiştirilir,
    //ordinalden string e aldık
    private Currency transactionCurrency;
    private LocalDate transactionDateTime;
    private String clientIpAddress;
    private String clientUrl;
    private String sessionActivityId;

    @Enumerated(EnumType.STRING)
    private TransationType transationType;

}
