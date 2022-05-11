package com.example.app.wallet.model.enumeration;

public enum Currency {
    TRY("Türk Lirası", "₺"),
    USD("Amerikan Doları", "$"),
    EUR("Euro", "€"),
    GBP("İngiliz Pound", "£");

    private String currencyName;
    private String currencySing;

    Currency(String currencyName, String currencySing) {
        this.currencyName = currencyName;
        this.currencySing = currencySing;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySing() {
        return currencySing;
    }

    public void setCurrencySing(String currencySing) {
        this.currencySing = currencySing;
    }
}
