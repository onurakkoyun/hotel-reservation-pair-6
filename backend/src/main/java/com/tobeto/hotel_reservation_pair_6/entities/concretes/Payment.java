package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import java.time.LocalDateTime;

import com.tobeto.hotel_reservation_pair_6.entities.abstracts.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment extends BaseEntity<Long> {
	
	//TODO: Düzenlenecek.
	//TODO: PaymentMethod ve Currency adında bir enum oluşturulabilir (Araştır currency).
	
	@Column(name = "stripe_payment_id")
	private String stripePaymentId;

	@Column(name = "payment_date")
	private LocalDateTime paymentDate;

	@Column(name = "amount")
	private double amount;
	
	@Column(name = "currency")
	private String currency;

	@Column(name = "payment_method")
	private String paymentMethod; // e.g., Credit Card, Bank Transfer, etc.

	@OneToOne(mappedBy = "payment")
    private Reservation reservation;

	@ManyToOne
	@JoinColumn(name = "guest_id", referencedColumnName = "id")
	private Guest guest;
}
