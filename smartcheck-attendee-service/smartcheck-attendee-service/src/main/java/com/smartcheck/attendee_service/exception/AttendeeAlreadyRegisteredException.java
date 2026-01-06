package com.smartcheck.attendee_service.exception;

public class AttendeeAlreadyRegisteredException extends RuntimeException {
    public AttendeeAlreadyRegisteredException(String message) {
        super(message);
    }
}
