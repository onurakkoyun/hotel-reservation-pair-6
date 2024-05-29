package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import com.tobeto.hotel_reservation_pair_6.entities.abstracts.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "reviews")
public class Review extends BaseEntity<Long>{
	
	@ManyToOne
    @JoinColumn(name = "guest_id")
	private Guest guest;
	
	@ManyToOne
    @JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
	@ManyToOne
    @JoinColumn(name = "reservation_id")
	private Reservation reservation;
	
	private String comment;
}
