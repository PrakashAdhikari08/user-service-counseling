package com.counseling.userservice.service;

import com.counseling.userservice.dto.BookingDto;
import com.counseling.userservice.dto.UserDto;

import java.util.UUID;

public interface UserService {
    Integer addAdmin(UserDto userDto);

    String getOrder();

    String setOrder(BookingDto bookingDto, String customerName);

}
