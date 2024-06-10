package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.fasterxml.jackson.databind.JsonNode;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Slf4j
@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeServiceImpl.class);
    private final RestTemplate restTemplate;
    private final String apiUrl = "https://api.frankfurter.app/latest?from={base}";


    public double getExchangeRate(String baseCurrency, String targetCurrency) {
        String url = apiUrl.replace("{base}", baseCurrency);
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        // Yanıtı loglama
        logger.info("API Response: {}", response);

        if (response == null || !response.has("rates")) {
            logger.error("Invalid API response: rates node is missing.");
            throw new RuntimeException("Invalid API response: rates node is missing.");
        }

        JsonNode ratesNode = response.get("rates");

        if (ratesNode == null) {
            logger.error("Invalid API response: rates node is missing.");
            throw new RuntimeException("Invalid API response: rates node is missing.");
        }

        JsonNode targetRateNode = ratesNode.get(targetCurrency);

        if (targetRateNode == null) {
            logger.error("Currency not found in the API response: " + targetCurrency);
            throw new RuntimeException("Currency not found in the API response: " + targetCurrency);
        }

        return targetRateNode.asDouble();
    }
}
