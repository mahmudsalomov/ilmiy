package com.ilmiy.component;

import com.ilmiy.model.others.RoleInterface;
import com.ilmiy.model.others.RoleName;
import com.ilmiy.model.user.Role;
import com.ilmiy.model.user.User;
import com.ilmiy.repository.RoleRepository;
import com.ilmiy.repository.UserRepository;
import com.ilmiy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void run(String... args) throws Exception {


        Role admin= Role.builder()
                .roleName(RoleName.ROLE_ADMIN)
                .build();
        roleRepository.save(admin);
        if (!userRepository.existsByUsername("admin")){
            User user=User.builder()
                    .username("admin")
                    .email("a")
                    .fullName("admin")
                    .active(true)
                    .password(passwordEncoder.encode("admin"))
                    .roles(Collections.singleton(admin))
                    .build();
            System.out.println(userRepository.save(user));
        }


        Role user= Role.builder()
                .roleName(RoleName.ROLE_USER)
                .build();
        roleRepository.save(user);
        if (!userRepository.existsByUsername("user")){
            User user1=User.builder()
                    .username("user")
                    .email("user@")
                    .fullName("user")
                    .active(true)
                    .password(passwordEncoder.encode("user"))
                    .roles(Collections.singleton(user))
                    .build();
            System.out.println(userRepository.save(user1));
        }

    }
}
