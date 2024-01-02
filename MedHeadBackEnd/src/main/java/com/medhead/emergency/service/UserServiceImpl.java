package com.medhead.emergency.service;

import com.medhead.emergency.entity.EmergencyUser;
import com.medhead.emergency.repository.EmergencyUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final EmergencyUserRepository emergencyUserRepository;

    public UserServiceImpl(EmergencyUserRepository emergencyUserRepositoryP) {
        emergencyUserRepository = emergencyUserRepositoryP;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmergencyUser user = emergencyUserRepository.findByUsername(username);
        return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getRole()));
    }

    /**
     * @see UserService .
     * @param user to save.
     * @return user.
     */
    @Override
    public EmergencyUser saveUser(final EmergencyUser user) {
        if (emergencyUserRepository.findByUsername(user.getUsername()) == null) {
            return emergencyUserRepository.save(user);
        } else {
            return null;
        }

    }

    @Override
    public boolean checkUserByUsername(String username) {
        return emergencyUserRepository.existsByUsername(username);
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}
