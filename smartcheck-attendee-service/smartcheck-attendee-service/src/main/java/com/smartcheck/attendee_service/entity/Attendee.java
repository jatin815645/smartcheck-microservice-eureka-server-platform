package com.smartcheck.attendee_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "attendees",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username", "event_id"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // from JWT (sub)
    @Column(nullable = false)
    private String username;

    // from request
    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendeeStatus status;

    @Column(nullable = false)
    private LocalDateTime registeredAt;

    private LocalDateTime checkedInAt;
}
