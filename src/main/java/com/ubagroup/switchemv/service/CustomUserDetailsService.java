package com.ubagroup.switchemv.service;


import com.ubagroup.switchemv.model.AppUser;
import com.ubagroup.switchemv.repository.AppUserRepository;
//import com.ubagroup.switchemv.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        AppUser user = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name()) // MERCHANT / ADMIN
                .build();
    }


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        System.out.println("Loading user: " + username);
//        AppUser user = repo.findByUsernameWithAuthorities(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        System.out.println("Found user: " + user.getUsername());
//        return User.builder()
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .roles(user.getRole().getRoleType().name())
//                .build();
//    }
}
