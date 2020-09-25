package com.counseling.userservice.service;

import com.counseling.userservice.dto.UserDto;

import java.util.UUID;

public interface UserService {
    Integer addAdmin(UserDto userDto);
}
