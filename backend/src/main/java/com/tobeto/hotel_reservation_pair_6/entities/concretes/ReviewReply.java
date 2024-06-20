package com.tobeto.hotel_reservation_pair_6.entities.concretes;

import com.tobeto.hotel_reservation_pair_6.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review_replies")
public class ReviewReply extends BaseEntity<Long> {

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "response", nullable = false)
    private String response;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;

    @OneToOne
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Review review;
}
