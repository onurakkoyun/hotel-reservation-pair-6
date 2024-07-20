package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iyzipay.model.Currency;
import com.tobeto.hotel_reservation_pair_6.entities.abstracts.BaseEntity;
import com.tobeto.hotel_reservation_pair_6.entities.enums.ReservationStatus;

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
@Table(name = "reservations")
public class Reservation extends BaseEntity<Long> {
	
	@Column(name = "created_date")
	private LocalDate createdDate;

	@Column(name = "check_in_date")
	private LocalDate checkInDate;

	@Column(name = "check_out_date")
	private LocalDate checkOutDate;

	@Column(name = "amount")
	private double amount;

	@Column(name = "currency")
	@Enumerated(EnumType.STRING)
	private Currency currency;

	@ManyToOne
	@JoinColumn(name = "status_id", referencedColumnName = "id")
	private ReservationStatus status;

	@OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment;

	@ManyToOne
	@JoinColumn(name = "guest_id", referencedColumnName = "id")
	private Guest guest;

	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private Room room;
}
