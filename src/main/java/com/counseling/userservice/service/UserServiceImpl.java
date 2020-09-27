package com.counseling.userservice.service;

import com.counseling.userservice.dao.UserRepository;
import com.counseling.userservice.domain.Role;
import com.counseling.userservice.domain.User;
import com.counseling.userservice.dto.BookingDto;
import com.counseling.userservice.dto.UserDto;
import com.counseling.userservice.feignproxy.OrderProxy;
import com.counseling.userservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderProxy orderProxy;

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
       return orderProxy.welcome();
//

    }
    @Override
    @Transactional
    public String setOrder(BookingDto bookingDto,String customerName) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        bookingDto.setCoustomerName(customerName);
//        HttpEntity<BookingDto> entity = new HttpEntity<BookingDto>(bookingDto,httpHeaders);
//
//        ResponseEntity<String> response = restTemplate.exchange
//                ("http://localhost:7073/booking-service/booking/v1/appointment/"+customerName,
//                        HttpMethod.POST, entity,String.class);
//        return response.getBody();

        ResponseEntity<String> responseEntity = orderProxy.makeAppointment(bookingDto, customerName);
        return  responseEntity.getBody();

    }
}
