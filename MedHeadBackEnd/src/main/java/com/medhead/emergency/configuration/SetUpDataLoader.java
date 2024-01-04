package com.medhead.emergency.configuration;

import com.medhead.emergency.entity.EmergencyUser;
import com.medhead.emergency.service.UserServiceImpl;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * For POC usage only
 */
@Component
public class SetUpDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;

    public SetUpDataLoader(UserServiceImpl userServiceP, PasswordEncoder passwordEncoderP, DataSource dataSourceP) {
        userService = userServiceP;
        passwordEncoder = passwordEncoderP;
        dataSource = dataSourceP;
    }

    /**
     * Create default user for demo.
     * @param event .
     */
    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!alreadySetup) {
            try (Connection conn = dataSource.getConnection()) {
                ScriptUtils.executeSqlScript(conn, new ClassPathResource("scripts/schema-medhead.sql"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            EmergencyUser user = new EmergencyUser("user", passwordEncoder.encode("user"), "USER");
            EmergencyUser admin = new EmergencyUser("admin", passwordEncoder.encode("admin"), "ADMIN");
            if (!userService.checkUserByUsername(user.getUsername())) {
                userService.saveUser(user);
            }
            if (!userService.checkUserByUsername(admin.getUsername())) {
                userService.saveUser(admin);
            }


            alreadySetup = true;
        }
    }
}
