package com.counseling.userservice.security.rolepermissionconfig;

import com.counseling.userservice.dao.PermissionRepository;
import com.counseling.userservice.dao.RoleRepository;
import com.counseling.userservice.security.LoginUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {

    @Autowired
    private ConfigServce configServce;

    @GetMapping("/configure-roles")
    public String rolePermissionConfig(){
        configServce.configureRoleAndPerm();
        return "Role-Permission Configured";
    }


    //Getting the user session
    @GetMapping("/get-user")
    public LoginUserResponse getUserSession(){
        return (LoginUserResponse) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }



}
