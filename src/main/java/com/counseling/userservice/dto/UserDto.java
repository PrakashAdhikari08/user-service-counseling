package com.counseling.userservice.dto;

import com.counseling.userservice.domain.Role;
import lombok.Data;

import javax.persistence.Enumerated;
import java.util.UUID;

@Data
public class UserDto {

    private Integer id;

    private String name;

    private String username;

    private String password;


    @Enumerated
    private Role role;

}
