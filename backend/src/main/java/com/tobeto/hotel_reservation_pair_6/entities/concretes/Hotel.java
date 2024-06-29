package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "hotels")
public class Hotel extends BaseEntity<Integer> {
	
	//TODO: Hotel için GetAllHotelsWithReviewsResponse nesnesi oluşturularak oteller listelenebilecek.
	//TODO: yada Review sayısını bulan başka bir method oluşturulacak. sonra karar ver.

	@Column(name = "hotel_name")
	private String hotelName;
	
	@Column(name = "is_breakfast_available")
	private boolean isBreakfastAvailable;
	
	@Column(name = "is_breakfast_included_in_price")
	private boolean IsBreakfastIncludedInPrice;
	
	@Column(name = "breakfast_price_per_person")
	private double breakfastPricePerPerson;
	
	@Column(name = "star_count")
    private int starCount;

	@Column(name = "rating_average")
	private Double ratingAverage;

	@Column(name = "first_address_line")
	private String firstAddressLine;

	@Column(name = "second_address_line")
	private String secondAddressLine;

	@Column(name = "city")
	private String city;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "province")
	private String province;

	@Column(name = "country")
	private String country;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Room> rooms;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "hotel_service", 
		joinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "hotel_feature_id"))
	private List<HotelFeature> hotelFeatures;

	@ManyToOne
	@JoinColumn(name = "manager_id", referencedColumnName = "id")
	private Manager manager;

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HotelImage> hotelImages;

	@JsonIgnore
	@OneToMany(mappedBy = "hotel")
	private List<Payment> payments;

	@JsonIgnore
	@OneToMany(mappedBy = "hotel")
	private List<RefundPayment> refundPayments;
}
