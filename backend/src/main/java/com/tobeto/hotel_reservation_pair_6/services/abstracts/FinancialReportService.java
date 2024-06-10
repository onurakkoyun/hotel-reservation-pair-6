package com.tobeto.hotel_reservation_pair_6.services.abstracts;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface FinancialReportService {
    double calculateTotalIncomeUSD(int hotelId, LocalDate startDate, LocalDate endDate);

    double calculateTotalRefundsUSD(int hotelId, LocalDate startDate, LocalDate endDate);

    Result calculateNetIncomeUSD(int hotelId, LocalDate startDate, LocalDate endDate);
}
