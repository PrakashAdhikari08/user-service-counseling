package com.counseling.userservice.dto;

import lombok.Data;

import javax.persistence.Enumerated;

@Data
public class UserDto {

    private Integer id;

    private String name;

    private String username;

    private String password;


//    @Enumerated
//    private Role role;

}
