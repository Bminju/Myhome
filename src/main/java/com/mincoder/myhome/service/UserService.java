package com.mincoder.myhome.service;

import com.mincoder.myhome.model.Role;
import com.mincoder.myhome.model.User;
import com.mincoder.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        public User save(User user){
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword); //암호화된 PWD 저장
            user.setEnabled(true);
            Role role = new Role();
            role.setId(1l); // role 1l 저장
            user.getRoles().add(role); //어떤 권한을 줄 것인지 저장 가능.
            return userRepository.save(user);
        }

}
