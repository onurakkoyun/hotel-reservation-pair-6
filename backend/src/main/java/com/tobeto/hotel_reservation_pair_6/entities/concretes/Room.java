package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tobeto.hotel_reservation_pair_6.entities.abstracts.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "rooms")
public class Room extends BaseEntity<Long>{
	
    @Column(name = "quantity")
	private int quantity;

    @Column(name = "room_price")
	private double roomPrice;

    @Column(name = "capacity")
	private int capacity;

    @Column(name = "square_meter_size")
	private int squareMeterSize;
	    
    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;
	
	@ManyToOne
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    private RoomType roomType;
	
	@JsonIgnore
	@OneToMany(mappedBy = "room")
    private List<Reservation> reservations;
    
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomBed> roomBeds;
    
    @ManyToMany
	@JoinTable(name = "room_service", 
	joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "room_feature_id"))
    private List<RoomFeature> roomFeatures;

}
