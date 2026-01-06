package com.smartcheck.event_service.repository;



import com.smartcheck.event_service.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}

