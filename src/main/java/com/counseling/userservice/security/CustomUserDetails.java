package com.counseling.userservice.security;

import com.counseling.userservice.dao.UserRepository;
import com.counseling.userservice.domain.RolePermissionMapping;
import com.counseling.userservice.domain.User;
import com.counseling.userservice.domain.UserRoleMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

@Component
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);

        user.orElseThrow(()-> new UsernameNotFoundException("Invalid Username"));

        User user1 = user.get();

        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for(UserRoleMapping userRoleMapping : user1.getUserRoleMappingList()){
            for(RolePermissionMapping rolePermissionMapping: userRoleMapping.getRole().getRolePermissionMappingList()){
                grantedAuthorities.add(new SimpleGrantedAuthority(rolePermissionMapping.getPermission().getPermissionName()));
            }
        }
        return new LoginUserResponse(user1.getUsername(),
        user1.getPassword(), true, false, false, false,
        grantedAuthorities,"You are loggedin");

    }
}
