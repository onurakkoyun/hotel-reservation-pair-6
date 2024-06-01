package com.tobeto.hotel_reservation_pair_6.entities.concretes;

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
@Table(name = "room_beds")
public class RoomBed extends BaseEntity<Integer> {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
	private Room room;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bed_id", referencedColumnName = "id", nullable = false)
    private Bed bed;

    private int quantity;
}
