package com.smartcheck.attendee_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterAttendeeRequest {

    @NotNull
    private Long eventId;
}
