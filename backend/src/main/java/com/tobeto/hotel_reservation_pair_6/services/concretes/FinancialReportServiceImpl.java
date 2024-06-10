package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.tobeto.hotel_reservation_pair_6.core.results.Result;
import com.tobeto.hotel_reservation_pair_6.core.results.SuccessResult;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Payment;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.RefundPayment;
import com.tobeto.hotel_reservation_pair_6.repositories.PaymentRepository;
import com.tobeto.hotel_reservation_pair_6.repositories.RefundPaymentRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.CurrencyExchangeService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.FinancialReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FinancialReportServiceImpl implements FinancialReportService {

    private final PaymentRepository paymentRepository;

    private final RefundPaymentRepository refundPaymentRepository;

    private final CurrencyExchangeService currencyExchangeService;

    @Override
    public double calculateTotalIncomeUSD(int hotelId, LocalDate startDate, LocalDate endDate) {
        List<Payment> payments = paymentRepository.findAllByHotel_IdAndPaymentDateBetween(hotelId, startDate, endDate);
        double totalIncome = payments.stream().mapToDouble(payment -> {
            if (!payment.getCurrency().name().equals("USD")){
                double exchangeRate = currencyExchangeService.getExchangeRate(payment.getCurrency().name(), "USD");
                return payment.getAmount() * exchangeRate;
            }
            else return payment.getAmount();

        }).sum();

        return totalIncome;
    }

    @Override
    public double calculateTotalRefundsUSD(int hotelId, LocalDate startDate, LocalDate endDate) {
        List<RefundPayment> refunds = refundPaymentRepository.findAllByHotel_IdAndRefundDateBetween(hotelId, startDate, endDate);
        double totalRefunds = refunds.stream().mapToDouble(refundPayment -> {
            if (!refundPayment.getCurrency().name().equals("USD")) {
                double exchangeRate = currencyExchangeService.getExchangeRate(refundPayment.getCurrency().name(), "USD");
                return refundPayment.getAmount() * exchangeRate;
            }
            else return refundPayment.getAmount();
        }).sum();

        return totalRefunds;
    }

    @Override
    public Result calculateNetIncomeUSD(int hotelId, LocalDate startDate, LocalDate endDate) {
        double totalIncomeUSD = calculateTotalIncomeUSD(hotelId, startDate, endDate);
        double totalRefundsUSD = calculateTotalRefundsUSD(hotelId, startDate, endDate);

        double netIncomeUSD = totalIncomeUSD - totalRefundsUSD;

        return new SuccessResult("$"+ netIncomeUSD);
    }
}
