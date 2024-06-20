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
@Table(name = "support_ticket_replies")
public class SupportTicketReply extends BaseEntity<Long> {

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "message", length = 1000)
    private String message;

    @ManyToOne
    @JoinColumn(name = "support_ticket_id", referencedColumnName = "id")
    private SupportTicket supportTicket;

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Manager manager;
}
