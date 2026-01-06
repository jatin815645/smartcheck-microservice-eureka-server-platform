package com.smartcheck.attendee_service.dto.response;

import com.smartcheck.attendee_service.entity.AttendeeStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AttendeeResponse {

    private Long id;
    private Long eventId;
    private String username;
    private AttendeeStatus status;
    private LocalDateTime registeredAt;
    private LocalDateTime checkedInAt;
}
