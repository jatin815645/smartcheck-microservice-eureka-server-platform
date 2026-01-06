package com.smartcheck.event_service.service;

import com.smartcheck.event_service.dto.request.EventRequest;
import com.smartcheck.event_service.dto.response.EventResponse;

import java.util.List;
import java.util.UUID;

public interface EventService {

    EventResponse createEvent(EventRequest request, String adminEmail);

    EventResponse getEvent(UUID eventId);

    List<EventResponse> getAllEvents();

    EventResponse updateEvent(UUID eventId, EventRequest request);

    void deleteEvent(UUID eventId);
}

