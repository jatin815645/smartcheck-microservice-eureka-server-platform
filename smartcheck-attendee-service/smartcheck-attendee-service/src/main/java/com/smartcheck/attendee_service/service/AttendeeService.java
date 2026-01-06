package com.smartcheck.attendee_service.service;


import java.util.List;


import com.smartcheck.attendee_service.entity.Attendee;
import com.smartcheck.attendee_service.entity.AttendeeStatus;

import java.util.List;

public interface AttendeeService {

    Attendee registerAttendee(Attendee attendee);

    Attendee checkInAttendee(String username, Long eventId);

    List<Attendee> getAttendeesByEvent(Long eventId);

    List<Attendee> getAttendeesByEventAndStatus(Long eventId, AttendeeStatus status);
}

