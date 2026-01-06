package com.smartcheck.event_service.controller;
import com.smartcheck.event_service.dto.request.EventRequest;
import com.smartcheck.event_service.dto.response.EventResponse;
import com.smartcheck.event_service.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    // ADMIN CREATE
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public EventResponse createEvent(@Valid @RequestBody EventRequest request,
                                     Authentication authentication) {
        String adminEmail = authentication.getName();
        return eventService.createEvent(request, adminEmail);
    }

    // GET ALL EVENTS
    @GetMapping
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }

    // GET SINGLE EVENT
    @GetMapping("/{id}")
    public EventResponse getEvent(@PathVariable UUID id) {
        return eventService.getEvent(id);
    }

    // ADMIN UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EventResponse updateEvent(@PathVariable UUID id,
                                     @Valid @RequestBody EventRequest request) {
        return eventService.updateEvent(id, request);
    }

    // ADMIN DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteEvent(@PathVariable UUID id) {
        eventService.deleteEvent(id);
    }
}

