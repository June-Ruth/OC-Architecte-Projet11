package com.medhead.emergency.repository;

import com.medhead.emergency.entity.EmergencyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyUserRepository extends JpaRepository<EmergencyUser, Integer> {

    EmergencyUser findByUsername(String username);

    boolean existsByUsername(String username);

    EmergencyUser save(EmergencyUser emergencyUser);

}
