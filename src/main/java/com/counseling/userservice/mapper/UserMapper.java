package com.counseling.userservice.mapper;

import com.counseling.userservice.domain.User;
import com.counseling.userservice.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    List<User> userList(List<UserDto> userDtoList);

    List<UserDto> userDtoList(List<User> userList);
}
