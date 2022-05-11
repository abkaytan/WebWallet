package com.example.app.wallet.exceptions;

public class NoEnoughBalanceForWithdrawException extends RuntimeException{
    public NoEnoughBalanceForWithdrawException(String msg) {
        super(msg);
    }
}
