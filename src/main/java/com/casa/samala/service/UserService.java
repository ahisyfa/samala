package com.casa.samala.service;

import com.casa.samala.entity.User;
import com.casa.samala.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Ahmad Isyfalana Amin
 * @version $Id: UserService.java, v 0.1 2023-12-30  14.17 Ahmad Isyfalana Amin Exp $
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserByUsername(String username){
        return  userRepository.findUserByUsername(username);
    }
}