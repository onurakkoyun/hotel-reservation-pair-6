package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import java.time.LocalDateTime;

import com.tobeto.hotel_reservation_pair_6.entities.abstracts.BaseEntity;

import jakarta.persistence.*;
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

	@Column(name = "payment_date")
	private LocalDateTime paymentDate;

	@Column(name = "amount")
	private double amount;
	
	@Column(name = "currency")
	private String currency;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservation reservation;

	@ManyToOne
	@JoinColumn(name = "guest_id", referencedColumnName = "id")
	private Guest guest;
}
