package com.counseling.userservice.service;

import com.counseling.userservice.dao.UserRepository;
import com.counseling.userservice.domain.Role;
import com.counseling.userservice.domain.User;
import com.counseling.userservice.dto.BookingDto;
import com.counseling.userservice.dto.UserDto;
import com.counseling.userservice.feignproxy.BookingProxy;
import com.counseling.userservice.mapper.UserMapper;
import com.counseling.userservice.rabbitmq.RabbitMqChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RabbitMqChannel rabbitMqChannel;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookingProxy bookingProxy;

    @Override
    @Transactional
    public Integer addAdmin(UserDto userDto) {
        User user = new User();
        user = userMapper.toEntity(userDto);
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public String getOrder() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
//
//        ResponseEntity<String> response = restTemplate.exchange
//                ("http://booking-service/booking-service/welcome", HttpMethod.GET, entity,String.class);
//        return response.getBody();

        //To make it dynamic on rest template use the @loadbalance and use service-id on ip and port number
       return bookingProxy.welcome();
//

    }
    @Override
    @Transactional
    public void setBooking(BookingDto bookingDto,String customerName) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        bookingDto.setCoustomerName(customerName);
//        HttpEntity<BookingDto> entity = new HttpEntity<BookingDto>(bookingDto,httpHeaders);
//
//        ResponseEntity<String> response = restTemplate.exchange
//                ("http://localhost:7073/booking-service/booking/v1/appointment/"+customerName,
//                        HttpMethod.POST, entity,String.class);
//        return response.getBody();

//        ResponseEntity<String> responseEntity = bookingProxy.makeAppointment(bookingDto, customerName);
//        return  responseEntity.getBody();
        Message<BookingDto> message = MessageBuilder.withPayload(bookingDto).build();

        rabbitMqChannel.booking().send(message);
        throw new RuntimeException("Problem is consumer");
//        System.out.println("Booking Request Made");

    }

    //for compensating transaction
    @Override
    @Transactional
    public void addCustomerAndBooking(UserDto userDto) {
        User user = new User();
        user = userMapper.toEntity(userDto);
        userRepository.save(user);
        BookingDto bookingDto = new BookingDto();
        bookingDto.setCoustomerName("Raju");
        bookingDto.setBookingDate(LocalDate.of(2021,12,02));
        bookingDto.setBookingTime(LocalTime.of(12,20));

        Message<BookingDto> message = MessageBuilder.withPayload(bookingDto).build();
        rabbitMqChannel.booking().send(message);
    }
}
