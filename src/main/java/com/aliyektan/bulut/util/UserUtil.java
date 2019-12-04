package com.aliyektan.bulut.util;

import com.aliyektan.bulut.entity.User;
import com.aliyektan.bulut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
public class UserUtil {

    @Autowired
    private UserRepository userRepository;

    public User getAuthenticatedUser(){
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication()
                .getName()).orElseThrow(() -> new EntityNotFoundException("Kullanıcı bulunamadı"));
    }

}
