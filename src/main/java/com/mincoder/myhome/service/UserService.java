package com.mincoder.myhome.service;

import com.mincoder.myhome.model.User;
import com.mincoder.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

        @Autowired
        private UserRepository userRepository;

        public User save(User user){
            return userRepository.save(user);
        }
}
