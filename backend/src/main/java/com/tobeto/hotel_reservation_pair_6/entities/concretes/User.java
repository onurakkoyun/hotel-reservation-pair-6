package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import com.tobeto.hotel_reservation_pair_6.entities.abstracts.BaseEntity;
import com.tobeto.hotel_reservation_pair_6.entities.enums.Role;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity<Integer> {
 //TODO: Security eklendikten sonra implements et UserDetails'ı

  @Column(name="first_name")
  private String firstName;

  @Column(name="last_name")
  private String lastName;

  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;

  @Column(name="phone_number")
  private String phoneNumber;

  @Column(name="profile_photo_url")
  private String profilePhotoUrl;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;
}
