package com.medhead.emergency.service;

import com.medhead.emergency.entity.EmergencyUser;

public interface UserService {

    /**
     * Save a new user or update an existing user.
     * @param user to save.
     * @return user saved.
     */
    EmergencyUser saveUser(EmergencyUser user);

    /**
     * Check the presence of user with same username.
     * @param username .
     * @return true if exists.
     */
    boolean checkUserByUsername(String username);
}
