package com.counseling.userservice.controller;

import com.counseling.userservice.dto.BookingDto;
import com.counseling.userservice.dto.UserDto;
import com.counseling.userservice.rabbitmq.RabbitMqChannel;
import com.counseling.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/user/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitMqChannel rabbitMqChannel;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String  hello(){
        return "Hello From user service";
    }

    @PostMapping("/appointment/{customerName}")
    public String  makeAppointment(@RequestBody BookingDto bookingDto, @PathVariable String customerName){
        userService.setBooking(bookingDto, customerName);
        return "Booking in Process";
    }

    @PostMapping("/admin/add")
    public ResponseEntity<String> addAdmin(@RequestBody UserDto userDto){
        userService.addAdmin(userDto);
        return new ResponseEntity<>("Admin Added", HttpStatus.CREATED);
    }

    @GetMapping("/all-order")
    public String customerOrder(){
       return userService.getOrder();
    }


    @PostMapping("/greet/{name}")
    public ResponseEntity<String> publish(@PathVariable String name) {
        String greeting = "Hello, " + name + "!";
        System.out.println(greeting);
        Message<String> msg = MessageBuilder.withPayload(greeting)
                .build();
        rabbitMqChannel.booking().send(msg);

        return new ResponseEntity<>("Greeting send", HttpStatus.OK);
    }

    @PostMapping("/add-customer/book")
    public ResponseEntity<String> addCustomerAndBooking(@RequestBody UserDto userDto){
        userService.addCustomerAndBooking(userDto);
        return new ResponseEntity<>("Customer Creation and Order Placing into process", HttpStatus.OK);
    }
}
