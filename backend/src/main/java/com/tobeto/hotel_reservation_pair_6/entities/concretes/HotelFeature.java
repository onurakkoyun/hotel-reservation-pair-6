package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "hotel_features")
public class HotelFeature extends BaseEntity<Integer>{
	
	@Column(name = "hotel_feature_name")
	private String hotelFeatureName;

	@ManyToMany(mappedBy = "hotelFeatures")
	private List<Hotel> hotel;
}
