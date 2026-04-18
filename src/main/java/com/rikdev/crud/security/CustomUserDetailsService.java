package com.rikdev.crud.security;

import com.rikdev.crud.entities.User;
import com.rikdev.crud.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

        private final UserRepository userRepository;

        public CustomUserDetailsService(UserRepository userRepository) {
                this.userRepository = userRepository;
        }

        @Override
        @Transactional(readOnly = true)
        public UserDetails loadUserByUsername(String username) {
                User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("No existe"));

                return new org.springframework.security.core.userdetails.User(
                                user.getUsername(),
                                user.getPassword_hash(),
                                user.getUserRoles().stream()
                                                .map(ur -> new SimpleGrantedAuthority(
                                                                "ROLE_" + ur.getRole().getName()))
                                                .toList());
        }
}
