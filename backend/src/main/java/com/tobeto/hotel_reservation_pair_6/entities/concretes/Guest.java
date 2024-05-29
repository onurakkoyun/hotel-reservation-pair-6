package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "guests")
public class Guest extends User{
	
	@JsonIgnore
    @OneToMany(mappedBy = "guest")
    private List<Reservation> reservations;
    
    @JsonIgnore
    @OneToMany(mappedBy = "guest")
    private List<Review> reviews;
}
