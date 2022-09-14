package com.springboot.blog.service.impl;

import com.springboot.blog.entity.User;
import com.springboot.blog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
    private final UserRepository userRepository ;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
       Optional<User> user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
       if(user.isEmpty()){
           throw new UsernameNotFoundException("User not found " + usernameOrEmail);
       }
//                .orElseThrow(()->new UsernameNotFoundException("User not found " + usernameOrEmail));
        return user.get();

    }




}
