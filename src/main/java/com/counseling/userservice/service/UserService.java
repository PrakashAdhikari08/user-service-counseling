package com.counseling.userservice.service;

import com.counseling.userservice.dto.BookingDto;
import com.counseling.userservice.dto.UserDto;

import java.util.UUID;

public interface UserService {
    Integer addAdmin(UserDto userDto);

    String getOrder();

    void setBooking(BookingDto bookingDto, String customerName);

    void addCustomerAndBooking(UserDto userDto);
}
