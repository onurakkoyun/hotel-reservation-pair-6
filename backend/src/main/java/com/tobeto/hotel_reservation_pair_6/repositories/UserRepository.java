package com.tobeto.hotel_reservation_pair_6.repositories;


import com.tobeto.hotel_reservation_pair_6.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);

}
