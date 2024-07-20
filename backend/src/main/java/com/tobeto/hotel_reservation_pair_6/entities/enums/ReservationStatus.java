package com.tobeto.hotel_reservation_pair_6.entities.enums;

import com.tobeto.hotel_reservation_pair_6.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation_statuses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationStatus extends BaseEntity<Integer> {

	@Column(name = "status", nullable = false, unique = true)
	private String status;
}
