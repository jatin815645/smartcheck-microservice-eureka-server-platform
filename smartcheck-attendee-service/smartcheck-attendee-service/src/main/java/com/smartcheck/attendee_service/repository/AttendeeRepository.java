package com.smartcheck.attendee_service.repository;


import com.smartcheck.attendee_service.entity.Attendee;
import com.smartcheck.attendee_service.entity.AttendeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

    // Prevent duplicate registration
    boolean existsByUsernameAndEventId(String username, Long eventId);

    // Fetch attendee for check-in
    Optional<Attendee> findByUsernameAndEventId(String username, Long eventId);

    // Admin / Event view
    List<Attendee> findAllByEventId(Long eventId);

    // Analytics / filters
    List<Attendee> findAllByEventIdAndStatus(Long eventId, AttendeeStatus status);
}

