package com.example.app.wallet.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class WalletAlreadyExistsException extends RuntimeException{



    public WalletAlreadyExistsException (String msg){
        super(msg);
    }


}
