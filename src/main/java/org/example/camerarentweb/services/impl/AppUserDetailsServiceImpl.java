//package org.example.camerarentweb.services.impl;
//
//import org.example.camerarentweb.repositories.UserRepository;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.stream.Collectors;
//
//public class AppUserDetailsServiceImpl implements UserDetailsService {
//    private UserRepository userRepository;
//
//    public AppUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//
//    // username = phonenumber в моей предметной области решил так сделать
//    @Override
//    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
//        return userRepository.findByPhoneNumber(phoneNumber)
//                .map(u -> new User(
//                        u.getPhoneNumber(),
//                        u.getPassword(),
//                        u.getRoles().stream()
//                                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getName().name()))
//                                .collect(Collectors.toList())
//                )).orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
//    }
//}