package com.example.app.wallet.exceptions;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String msg) {
        super(msg);
    }

}
