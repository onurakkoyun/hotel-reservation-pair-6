package com.tobeto.hotel_reservation_pair_6.services.abstracts;

public interface CurrencyExchangeService {
    double getExchangeRate(String baseCurrency, String targetCurrency);
}
