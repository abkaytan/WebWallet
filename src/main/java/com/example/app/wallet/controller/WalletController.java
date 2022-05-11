package com.example.app.wallet.controller;

import com.example.app.wallet.dto.WalletDTO;
import com.example.app.wallet.model.Wallet;
import com.example.app.wallet.model.WalletServiceTransactionLogger;
import com.example.app.wallet.service.WalletService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    /*
    @Autowired
    @Qualifier("/mysqldatasource")
    DataSource dataSource
    //datasource configuration üzerinden database bağlantısını controller içinden yönetmek için kullanılan yapı
     */

    @PostMapping("/save-wallet")
    public ResponseEntity<Wallet> saveWallet (@RequestBody @Valid WalletDTO walletDTO) {

        Optional<Wallet> resultOptional =  walletService.save_wallet(walletDTO);

        if(resultOptional.isPresent()){

            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/deposit/{customerId}/{currency}/{amount}")
    public ResponseEntity<Wallet> depositToWallet(@PathVariable long customerId,
                                                  @PathVariable ("currency")String currencyName,
                                                  @PathVariable double amount) {
        Optional<Wallet> resultOptional =  walletService.deposit(customerId, currencyName, amount);

        if(resultOptional.isPresent()){

            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/withdraw/{customerId}/{currency}/{amount}")
    public ResponseEntity<Wallet> withdrawFromWallet(@PathVariable long customerId,
                                                  @PathVariable ("currency")String currencyName,
                                                  @PathVariable double amount) {
        Optional<Wallet> resultOptional =  walletService.withdraw(customerId, currencyName, amount);

        if(resultOptional.isPresent()){

            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/getWallets")
    public ResponseEntity<List<Wallet>> getWallets(@RequestParam int customerId) {
        Optional<List<Wallet>> wallets = walletService.getWallets(customerId);
        return new ResponseEntity<>(wallets.get(), HttpStatus.OK);
    }

    //pagenumber ve pagesize için oluşturulan request paramatrelerinde required false olarak belirtirsek
    // aşşağıdaki örneğindeki gibi, eğer paramatre göndermezsek defaulttları olan true çalışır.
    @GetMapping("/get-transactions-by-date")
    public ResponseEntity<Page<List<WalletServiceTransactionLogger>>> getAllTransactionWithDate(
            @ApiParam(value = "transaction query for wallet usage", example = "05/07/2021", required = true)
            @RequestParam String transactionDate,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @PageableDefault(page = 0, size = 10)Pageable pageable) {
        return new ResponseEntity<>(this.walletService.getAllTransactionsWithDate(transactionDate, pageNumber, pageSize, pageable), HttpStatus.OK);

    }


}
