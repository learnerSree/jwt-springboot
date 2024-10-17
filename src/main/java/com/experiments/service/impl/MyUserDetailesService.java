package com.experiments.service.impl;

import com.experiments.entity.MyUserDetails;
import com.experiments.entity.User;
import com.experiments.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class  MyUserDetailesService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {

        User user = userRepository.findByUserName( username );
        return new MyUserDetails(user);
    }
}
