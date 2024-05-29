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
@Table(name = "room_beds")
public class RoomBed extends BaseEntity<Integer> {

	@ManyToOne
	@JoinColumn(name = "room_id", referencedColumnName = "id")
	private Room room;
	
	@ManyToOne
    @JoinColumn(name = "bed_id", referencedColumnName = "id")
    private Bed bed;

    private int quantity;
}
