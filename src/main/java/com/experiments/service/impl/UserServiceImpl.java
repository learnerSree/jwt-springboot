package com.experiments.service.impl;

import com.experiments.entity.User;
import com.experiments.repository.UserRepository;
import com.experiments.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder( 12 );

    @Override
    public User findByUserName(String userName) {

        return userRepository.findByUserName( userName );
    }

    @Override
    public User save(User user) {

        user.setPassword( encoder.encode( user.getPassword() ));
        return userRepository.save( user );
    }

    @Override
    public String verify( User user ) {

        try{
        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( user.getUserName(), user.getPassword()));

            if( authentication.isAuthenticated() ){

                return jwtService.generateToken( user.getUserName() );
            }
            else {
                return "Failed";
            }

        }catch ( Exception e){

            return "Failed";
        }
    }
}
