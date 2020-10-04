package com.counseling.userservice.rabbitmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface RabbitMqChannel {

    @Output("user-service")
    MessageChannel booking();

    @Input("booking-service-booked")
    SubscribableChannel bookingConformed();

}
