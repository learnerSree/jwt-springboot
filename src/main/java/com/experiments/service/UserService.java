package com.experiments.service;

import com.experiments.entity.User;

public interface UserService {
    public User findByUserName( String userName );
    public User save( User user );
    public String verify( User user );
}
