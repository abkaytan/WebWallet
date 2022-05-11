package com.example.app.wallet.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletAppErrorResponse {
    private int status;
    private String message;
    private long timestamp;


}
