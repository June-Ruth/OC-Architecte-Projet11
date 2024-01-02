package com.medhead.emergency.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class EmergencyUser {

    public EmergencyUser(final String usernameP, final String passwordP, final String roleP) {
        username = usernameP;
        password = passwordP;
        role = roleP;
    }

    public EmergencyUser() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer idP) {
        id = idP;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usernameP) {
        username = usernameP;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordP) {
        password = passwordP;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String roleP) {
        role = roleP;
    }
}
