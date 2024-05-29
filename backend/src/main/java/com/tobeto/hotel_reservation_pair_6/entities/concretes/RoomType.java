package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tobeto.hotel_reservation_pair_6.entities.abstracts.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name = "room_types")
public class RoomType extends BaseEntity<Integer> {

	@Column(name = "room_type_name")
	private String roomTypeName;

	@JsonIgnore
	@OneToMany(mappedBy = "roomType")
	private List<Room> rooms;
	
	
}
