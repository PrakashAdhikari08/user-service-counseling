package com.counseling.userservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class ApplicationAuthProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private CustomUserDetails customUserDetails;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //this will be  used for password check
    //UserDetails from UI and UsernamePasswordAuthenticationToken from
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        if (userDetails.getPassword() == null || usernamePasswordAuthenticationToken.getCredentials().toString() == null)
            throw new BadCredentialsException("Invalid Password from Null Check");
        if (!passwordEncoder.matches((String) usernamePasswordAuthenticationToken.getCredentials(), userDetails.getPassword()))
            throw new BadCredentialsException("Invalid Password");

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        return customUserDetails.loadUserByUsername(username);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
