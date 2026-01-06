package com.smartcheck.attendee_service.controller;

import com.smartcheck.attendee_service.entity.Attendee;
import com.smartcheck.attendee_service.entity.AttendeeStatus;
import com.smartcheck.attendee_service.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    // 1️⃣ Register attendee
    @PostMapping("/register")
    public ResponseEntity<Attendee> registerAttendee(@RequestBody Attendee attendee) {
        Attendee savedAttendee = attendeeService.registerAttendee(attendee);
        return new ResponseEntity<>(savedAttendee, HttpStatus.CREATED);
    }

    // 2️⃣ Check-in attendee
    @PutMapping("/check-in")
    public ResponseEntity<Attendee> checkInAttendee(
            @RequestParam String username,
            @RequestParam Long eventId
    ) {
        Attendee attendee = attendeeService.checkInAttendee(username, eventId);
        return ResponseEntity.ok(attendee);
    }

    // 3️⃣ Get all attendees by event
    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Attendee>> getAttendeesByEvent(
            @PathVariable Long eventId
    ) {
        return ResponseEntity.ok(attendeeService.getAttendeesByEvent(eventId));
    }

    // 4️⃣ Get attendees by event + status
    @GetMapping("/event/{eventId}/status/{status}")
    public ResponseEntity<List<Attendee>> getAttendeesByEventAndStatus(
            @PathVariable Long eventId,
            @PathVariable AttendeeStatus status
    ) {
        return ResponseEntity.ok(
                attendeeService.getAttendeesByEventAndStatus(eventId, status)
        );
    }
}
