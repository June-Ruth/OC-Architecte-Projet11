package com.medhead.emergency.service;

import com.medhead.emergency.entity.EmergencyUser;
import com.medhead.emergency.repository.EmergencyUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    
    @Mock
    private EmergencyUserRepository emergencyUserRepository;

    private UserService userService;

    EmergencyUser user;

    @BeforeEach
    void beforeEach() {
        userService = new UserServiceImpl(emergencyUserRepository);
        user = new EmergencyUser("user", "user", "USER");
    }

    @Test
    void saveUserThatExistsTest() {
        when(emergencyUserRepository.findByUsername(any())).thenReturn(user);
        assertNull(userService.saveUser(user));
        verify(emergencyUserRepository, times(1)).findByUsername(any());
        verify(emergencyUserRepository, times(0)).save(any());

    }

    @Test
    void saveUserThatNotExistsTest() {
        when(emergencyUserRepository.findByUsername(any())).thenReturn(null);
        when(emergencyUserRepository.save(any())).thenReturn(user);
        assertEquals(userService.saveUser(user), user);
        verify(emergencyUserRepository, times(1)).findByUsername(any());
        verify(emergencyUserRepository, times(1)).save(any());
    }

    @Test
    void checkByUsernameThatExistsTest() {
        when(emergencyUserRepository.existsByUsername(any())).thenReturn(true);
        assertTrue(userService.checkUserByUsername(user.getUsername()));
        verify(emergencyUserRepository, times(1)).existsByUsername(any());
    }

    @Test
    void checkByUsernameThatNotExistsTest() {
        when(emergencyUserRepository.existsByUsername(any())).thenReturn(false);
        assertFalse(userService.checkUserByUsername(user.getUsername()));
        verify(emergencyUserRepository, times(1)).existsByUsername(any());
    }
}
