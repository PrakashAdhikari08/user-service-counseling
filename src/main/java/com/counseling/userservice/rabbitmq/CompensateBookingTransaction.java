package com.counseling.userservice.rabbitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CompensateBookingTransaction {

    public final String BOOKING_USER_COMPENSATION = "booking-compensate-user";

    @Input(BOOKING_USER_COMPENSATION)
    SubscribableChannel bookingCompensateUser();
}
