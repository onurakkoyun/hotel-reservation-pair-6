package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tobeto.hotel_reservation_pair_6.entities.abstracts.BaseEntity;
import com.tobeto.hotel_reservation_pair_6.entities.enums.ReservationStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "reservations")
public class Reservation extends BaseEntity<Long> {
	
	//TODO: Ödeme alımı devreye alındığında düzenlenecek. (Payment)
	
	@Column(name = "created_date")
	private LocalDate createdDate;

	@Column(name = "check_in_date")
	private LocalDate checkInDate;

	@Column(name = "check_out_date")
	private LocalDate checkOutDate;

	@Column(name = "total_amount")
	private double totalAmount;
	
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

	@ManyToOne
	@JoinColumn(name = "guest_id", referencedColumnName = "id")
	private Guest guest;

	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private Room room;

	@JsonIgnore
	@OneToMany(mappedBy = "reservation")
	private List<Review> reviews;

}
