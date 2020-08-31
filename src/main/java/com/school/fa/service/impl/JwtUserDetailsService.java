package com.school.fa.service.impl;

import com.school.fa.entity.Role;
import com.school.fa.entity.User;
import com.school.fa.repository.RoleRepo;
import com.school.fa.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepo.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(email);
        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final org.springframework.security.core.userdetails.User user = userRepo.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User '" + username + "' not found");
//        }
//
//        return org.springframework.security.core.userdetails.User//
//                .withUsername(username)//
//                .password(user.getPassword())//
//                .authorities(user.getRoles())//
//                .accountExpired(false)//
//                .accountLocked(false)//
//                .credentialsExpired(false)//
//                .disabled(false)//
//                .build();
//    }
}
