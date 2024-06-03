package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.tobeto.hotel_reservation_pair_6.entities.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "manager_id", referencedColumnName = "id")
@Table(name = "managers")
public class Manager extends User {

	@Column(name = "company_name")
	private String companyName;

	@JsonIgnore
	@OneToMany(mappedBy = "manager")
	private List<Hotel> hotels;
}
