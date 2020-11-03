package com.counseling.userservice.security.rolepermissionconfig;

import com.counseling.userservice.dao.*;
import com.counseling.userservice.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfigServiceImpl  implements ConfigServce{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionMappingRepository rolePermissionMappingRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleMappingRepository userRoleMappingRepository;

    @Override
    @Transactional
    public void configureRoleAndPerm() {

        //Roles Created
        Role role = new Role();
        role.setRoleName("USER");
        role = roleRepository.save(role);

        Role role1 = new Role();
        role1.setRoleName("ADMIN");
        roleRepository.save(role1);

        //permission created
        Permission permission = new Permission();
        permission.setPermissionName("All_API");
        permissionRepository.save(permission);

        Permission permission1 = new Permission();
        permission1.setPermissionName("User_API");
        permissionRepository.save(permission1);

        //same role 2 permission for role
        RolePermissionMapping rolePermissionMapping = new RolePermissionMapping();
        rolePermissionMapping.setRole(role);
        rolePermissionMapping.setPermission(permission);
        rolePermissionMappingRepository.save(rolePermissionMapping);

        RolePermissionMapping rolePermissionMapping1 = new RolePermissionMapping();
        rolePermissionMapping1.setRole(role);
        rolePermissionMapping1.setPermission(permission1);
        rolePermissionMappingRepository.save(rolePermissionMapping1);

        //role1 has only one permission
        RolePermissionMapping rolePermissionMapping2 = new RolePermissionMapping();
        rolePermissionMapping2.setRole(role1);
        rolePermissionMapping2.setPermission(permission1);
        rolePermissionMappingRepository.save(rolePermissionMapping2);

        User user = new User();
        user.setUsername("test@gmail.com");
        user.setPassword(bCryptPasswordEncoder.encode("test1234"));

        user = userRepository.save(user);

        UserRoleMapping userRoleMapping = new UserRoleMapping();
        userRoleMapping.setRole(role);
        userRoleMapping.setUser(user);
        userRoleMappingRepository.save(userRoleMapping);


    }
}
