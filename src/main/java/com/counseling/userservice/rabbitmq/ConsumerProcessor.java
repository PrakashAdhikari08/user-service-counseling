package com.counseling.userservice.rabbitmq;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(RabbitMqChannel.class)
public class ConsumerProcessor {

    @StreamListener(target = "booking-service-booked")
    public void conformedBooking(String name){
        System.out.println("Booking Conformed  " + name);
    }
}
