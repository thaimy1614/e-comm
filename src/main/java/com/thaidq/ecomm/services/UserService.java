package com.thaidq.ecomm.services;

import com.thaidq.ecomm.models.User;
import com.thaidq.ecomm.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
