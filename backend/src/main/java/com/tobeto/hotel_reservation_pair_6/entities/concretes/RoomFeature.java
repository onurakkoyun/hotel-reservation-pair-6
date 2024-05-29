package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import java.util.List;

import com.tobeto.hotel_reservation_pair_6.entities.abstracts.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
@Table(name = "room_features")
public class RoomFeature extends BaseEntity<Integer>{
	
	@Column(name = "feature_name")
	private String featureName;
	
	@ManyToMany(mappedBy = "roomFeatures")
	private List<Room> room;
}
