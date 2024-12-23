package org.example.camerarentweb.services.impl;

import org.example.camerarentweb.entities.User;
import org.example.camerarentweb.entities.UserRole;
import org.example.camerarentweb.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class AppUserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public AppUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // username = email в моей предметной области решил так сделать
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("LOAD USER BY USERNAME");
        Optional<User> user =
                userRepository.findByEmail(username) == null ? Optional.empty() :
                        Optional.of(userRepository.findByEmail(username));
        System.out.println("USER FROM App User Details Service Impl: " + user.get());
        return user
                .map(u -> new org.springframework.security.core.userdetails.User(
                        u.getEmail(),
                        u.getPassword(),
                        getAuthorities(u.getRole())
                )).orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
    }
    //private List<SimpleGrantedAuthority> getAuthorities(UserRole userRole){
    private List<SimpleGrantedAuthority> getAuthorities(UserRole userRole){
        //return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        System.out.println("ROLES CHECK");
        switch (userRole){
            case ADMIN:
                return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_USER")
                 );
            case USER:
                return List.of(new SimpleGrantedAuthority("ROLE_USER"));
            default:
                return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }
}

