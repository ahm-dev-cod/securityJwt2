package com.example.myuser.services;

import com.example.myuser.entities.MyUser;
import com.example.myuser.repositories.MyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

    private MyUserRepository userRepository;
    @Override
    @Transactional(readOnly = true) // Explicite, mais optionnel
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Chargement utilisateur : " + username);
        MyUser user = userRepository.findByUsername(username);
        return user;
    }
    }
