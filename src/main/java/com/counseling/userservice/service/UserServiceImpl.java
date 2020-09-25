package com.counseling.userservice.service;

import com.counseling.userservice.dao.UserRepository;
import com.counseling.userservice.domain.Role;
import com.counseling.userservice.domain.User;
import com.counseling.userservice.dto.UserDto;
import com.counseling.userservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public Integer addAdmin(UserDto userDto) {
        User user = new User();
        user = userMapper.toEntity(userDto);
        user.setRole(Role.ROLE_ADMIN);
        userRepository.save(user);
        return user.getId();
    }
}
