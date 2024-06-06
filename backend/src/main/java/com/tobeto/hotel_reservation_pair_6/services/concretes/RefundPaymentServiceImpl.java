package com.tobeto.hotel_reservation_pair_6.services.concretes;

import com.iyzipay.Options;
import com.iyzipay.model.Locale;
import com.iyzipay.model.Refund;
import com.iyzipay.request.CreateRefundRequest;
import com.tobeto.hotel_reservation_pair_6.core.utilities.exceptions.types.BusinessException;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.Payment;
import com.tobeto.hotel_reservation_pair_6.entities.concretes.RefundPayment;
import com.tobeto.hotel_reservation_pair_6.repositories.PaymentRepository;
import com.tobeto.hotel_reservation_pair_6.repositories.RefundPaymentRepository;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.PaymentService;
import com.tobeto.hotel_reservation_pair_6.services.abstracts.RefundPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class RefundPaymentServiceImpl implements RefundPaymentService {

    private final RefundPaymentRepository refundPaymentRepository;

    private final PaymentService paymentService;

    @Value("${iyzico.api.key}")
    private String apiKey;

    @Value("${iyzico.secret.key}")
    private String secretKey;

    @Value("${iyzico.base.url}")
    private String baseUrl;


    @Override
    public RefundPayment createRefund(long paymentId, double amount) {

        Payment payment = paymentService.findById(paymentId);

        Options options = new Options();
        options.setApiKey(apiKey);
        options.setSecretKey(secretKey);
        options.setBaseUrl(baseUrl);

        CreateRefundRequest request = new CreateRefundRequest();
        request.setLocale(Locale.TR.getValue());
        request.setPaymentTransactionId(payment.getPaymentTransactionId());
        request.setConversationId(request.getConversationId());
        request.setPrice(new BigDecimal(amount));
        request.setCurrency(payment.getCurrency().name());

        Refund refund = Refund.create(request, options);

        RefundPayment refundPayment = new RefundPayment();
        refundPayment.setRefundDate(LocalDate.now());
        refundPayment.setAmount(amount);
        refundPayment.setStatus(refund.getStatus());
        refundPayment.setPayment(payment);

        refundPaymentRepository.save(refundPayment);

        return refundPayment;
    }
}
