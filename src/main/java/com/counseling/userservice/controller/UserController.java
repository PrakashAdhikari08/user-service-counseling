package com.counseling.userservice.controller;

import com.counseling.userservice.dto.UserDto;
import com.counseling.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user/v1/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String  hello(){
        return "Hello From user service";
    }

    @PostMapping("/admin/add")
    public ResponseEntity<String> addAdmin(@RequestBody UserDto userDto){
        userService.addAdmin(userDto);
        return new ResponseEntity<>("Admin Added", HttpStatus.CREATED);
    }
}
