package com.smartcheck.attendee_service.service.impl;

import com.smartcheck.attendee_service.dto.request.CheckInRequest;
import com.smartcheck.attendee_service.dto.request.RegisterAttendeeRequest;
import com.smartcheck.attendee_service.dto.response.AttendeeResponse;
import com.smartcheck.attendee_service.entity.Attendee;
import com.smartcheck.attendee_service.entity.AttendeeStatus;
import com.smartcheck.attendee_service.repository.AttendeeRepository;
import com.smartcheck.attendee_service.service.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;

    // 1️⃣ Register attendee
    @Override
    public Attendee registerAttendee(Attendee attendee) {

        boolean exists = attendeeRepository.existsByUsernameAndEventId(
                attendee.getUsername(),
                attendee.getEventId()
        );

        if (exists) {
            throw new RuntimeException("Attendee already registered for this event");
        }

        attendee.setStatus(AttendeeStatus.REGISTERED);
        return attendeeRepository.save(attendee);
    }

    // 2️⃣ Check-in attendee
    @Override
    public Attendee checkInAttendee(String username, Long eventId) {

        Attendee attendee = attendeeRepository
                .findByUsernameAndEventId(username, eventId)
                .orElseThrow(() -> new RuntimeException("Attendee not found"));

        attendee.setStatus(AttendeeStatus.CHECKED_IN);
        return attendeeRepository.save(attendee);
    }

    // 3️⃣ Get all attendees of an event
    @Override
    public List<Attendee> getAttendeesByEvent(Long eventId) {
        return attendeeRepository.findAllByEventId(eventId);
    }

    // 4️⃣ Get attendees by event + status
    @Override
    public List<Attendee> getAttendeesByEventAndStatus(Long eventId, AttendeeStatus status) {
        return attendeeRepository.findAllByEventIdAndStatus(eventId, status);
    }
}
