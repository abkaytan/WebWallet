package com.example.app.wallet.mappers;

import com.example.app.wallet.dto.WalletDTO;
import com.example.app.wallet.model.Wallet;
import com.example.app.wallet.service.WalletService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class WalletMapper {

    @Autowired
    protected WalletService walletService;


    //manuel mapleme yapmak için @Mapping annotationu kullanıyoruz.
    @Mapping(target= "customer", expression = "java(walletService.findCustomerById(walletDTO.getCustomerId()))")
    @Mapping(target= "createDate", expression = "java(java.time.LocalDate.now())")
    public abstract Wallet mapFromWalletDTOtoWallet(WalletDTO walletDTO);
    public abstract WalletDTO mapFromWallettoWalletDTO(Wallet wallet);

}
