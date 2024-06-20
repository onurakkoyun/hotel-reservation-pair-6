package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import com.tobeto.hotel_reservation_pair_6.entities.abstracts.BaseEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
public class Review extends BaseEntity<Long>{

	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	@Column(name = "comment", nullable = false)
	private String comment;

	@Column(name = "rating", nullable = false)
	private int rating;
	
	@ManyToOne
    @JoinColumn(name = "guest_id")
	private Guest guest;
	
	@ManyToOne
    @JoinColumn(name = "hotel_id")
	private Hotel hotel;

	@OneToOne(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
	private ReviewReply replyReview;
}
