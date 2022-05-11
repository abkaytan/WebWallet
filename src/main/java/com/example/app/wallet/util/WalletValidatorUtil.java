package com.example.app.wallet.util;

import com.example.app.wallet.exceptions.BadRequestException;
import com.example.app.wallet.exceptions.TransactionDateTimeParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//utility classlar' da uygulamanın bir çok yerinde kullanacağımız metodları toparladığımız sınıflardır diyebiliriz,
// bu sebepten dolayı nesnelerini oluşturma ihtiyacımız olmadığı için metodlarını da static yaparız
// kısaca utility classlar private constructor ve static metodlar içerir.

public class WalletValidatorUtil {

    public static void validateWalletBalance(double balance) {

        if(balance < 0) {
            throw new BadRequestException(ErrorMessageConstants.BALANCE_IS_MINUS);
        }

    }

    public static void validateTransactionDate(String transactionDate, DateTimeFormatter formatter) {

        try {
            LocalDate.parse(transactionDate, formatter);
        } catch (DateTimeParseException e) {
            throw new TransactionDateTimeParseException(ErrorMessageConstants.DATE_FORMAT_WRONG + transactionDate);
        }

    }
}
