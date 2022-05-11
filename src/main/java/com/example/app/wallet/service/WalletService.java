package com.example.app.wallet.service;

import com.example.app.wallet.dto.WalletDTO;
import com.example.app.wallet.exceptions.BadRequestException;
import com.example.app.wallet.exceptions.CustomerNotFoundException;
import com.example.app.wallet.exceptions.NoEnoughBalanceForWithdrawException;
import com.example.app.wallet.mappers.WalletMapper;
import com.example.app.wallet.model.Customer;
import com.example.app.wallet.model.Wallet;
import com.example.app.wallet.model.WalletServiceTransactionLogger;
import com.example.app.wallet.model.enumeration.Currency;
import com.example.app.wallet.model.enumeration.TransationType;
import com.example.app.wallet.repository.CustomerRepository;
import com.example.app.wallet.repository.TransactionLoggerRepository;
import com.example.app.wallet.repository.WalletRepository;
import com.example.app.wallet.exceptions.WalletAlreadyExistsException;
import com.example.app.wallet.util.ClientRequestInfo;
import com.example.app.wallet.util.ErrorMessageConstants;
import com.example.app.wallet.util.WalletValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletService {

    @Autowired
    private  WalletRepository walletRepository;
    @Autowired
    private  CustomerRepository customerRepository;
    @Autowired
    private  WalletMapper walletMapper;
    @Autowired
    private ClientRequestInfo clientRequestInfo;
    @Autowired
    private TransactionLoggerRepository transactionLoggerRepository;

    @Transactional
    public Optional<Wallet> save_wallet(WalletDTO walletDTO){

        this.validateRequest(walletDTO);


        Wallet wallet = walletMapper.mapFromWalletDTOtoWallet(walletDTO);
        if(walletRepository.selectExistsWalletWithSameCurrency(wallet.getCurrency(), wallet.getCustomer().getId())){

            throw new WalletAlreadyExistsException("Wallet with currency type : " + wallet.getCurrency().getCurrencyName()
            + "is already exists!!");

        }

        return Optional.of(walletRepository.save(wallet));
    }

    private void validateRequest(WalletDTO walletDTO) {
        WalletValidatorUtil.validateWalletBalance(walletDTO.getBalance());
    }

    public Customer findCustomerById(long customerId) {
        Customer foundCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with ID : %d could not found!!!" , customerId)));
        return foundCustomer;
    }

    //transactionalı aynı anda yapılacak işlemlere karşı thread olarak ekledik 4.1.3 - 50.00
    @Transactional
    public Optional<Wallet> deposit(long customerId, String currencyName, double amount) {
        //optional içerisine sarıyoruz ki dönemezse nullpointerı kendimiz buradan yönetebilelim.
        Optional<Wallet> wallet = getWallet(customerId, currencyName);
        wallet.get().setBalance(wallet.get().getBalance()+ amount);
        walletRepository.save(wallet.get());
        this.saveTransactionToDatabase(wallet.get(), amount, TransationType.DEPOSIT);
        return wallet;

    }

    @Transactional
    public Optional<Wallet> withdraw(long customerId, String currencyName, double amount) {
        Optional<Wallet> wallet = getWallet(customerId, currencyName);
        if(amount > wallet.get().getBalance()){
            throw new NoEnoughBalanceForWithdrawException(ErrorMessageConstants.NO_ENOUGH_BALANCE + amount + " "
                    + wallet.get().getCurrency().getCurrencySing());
        }
        wallet.get().setBalance(wallet.get().getBalance()- amount);
        walletRepository.save(wallet.get());
        this.saveTransactionToDatabase(wallet.get(), amount, TransationType.WITHDRAW);
        return wallet;

    }

    private void saveTransactionToDatabase(Wallet wallet, double amount, TransationType transationType) {
        WalletServiceTransactionLogger transactionLogger = new WalletServiceTransactionLogger();
        transactionLogger.setCustomerId(wallet.getCustomer().getId());
        if(transationType.equals(TransationType.DEPOSIT)) {
            transactionLogger.setBalanceBefore(wallet.getBalance() - amount);
        } else {
            transactionLogger.setBalanceBefore(wallet.getBalance() + amount);
        }
        //transactionLogger.setBalanceBefore(wallet.getBalance());
        transactionLogger.setBalanceAfter(wallet.getBalance());
        transactionLogger.setTransactionAmount(amount);
        transactionLogger.setTransactionCurrency(wallet.getCurrency());
        transactionLogger.setTransactionDateTime(LocalDate.now());
        transactionLogger.setClientUrl(clientRequestInfo.getClientUrl());
        transactionLogger.setClientIpAddress(clientRequestInfo.getClientIpAddress());
        transactionLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());
        transactionLogger.setTransationType(transationType);
        this.transactionLoggerRepository.save(transactionLogger);

    }

    //4-2-3 - 30.00
    private Optional<Wallet> getWallet(long customerId, String currencyName) {
        Customer customer = this.findCustomerById(customerId);
        Optional<Wallet> wallet = customer.getWallets().stream().filter(w -> w.getCurrency().equals(Currency.valueOf(currencyName))).findFirst();
        if(!wallet.isPresent()){
            throw new BadRequestException("Customer: "+ customer.getFirstName() + " " + customer.getLastName() + "doesnt have wallet with"
            + currencyName);
        }
        return wallet;
    }


    public Optional<List<Wallet>> getWallets(int customerId) {
        Customer customer = this.findCustomerById(customerId);
        Optional<List<Wallet>> wallets = Optional.of(customer.getWallets());

        if(wallets.get().isEmpty()) {
            wallets = Optional.of(walletRepository.findAll());
        }

        return wallets;
    }

    public Page<List<WalletServiceTransactionLogger>> getAllTransactionsWithDate(String transactionDate, Integer page, Integer size, Pageable pageable) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        WalletValidatorUtil.validateTransactionDate(transactionDate, formatter);
        LocalDate transactionDateResult = LocalDate.parse(transactionDate, formatter);
        pageable = PageRequest.of(1,20);
        if(page != null && size !=null) {
            //pageable= PageRequest.of(page, size, Sort.by("aaaa").ascending());
            pageable= PageRequest.of(page, size);
        }
        return this.transactionLoggerRepository.findAllTransactionByTransactionDate(transactionDateResult, pageable);

    }
}
