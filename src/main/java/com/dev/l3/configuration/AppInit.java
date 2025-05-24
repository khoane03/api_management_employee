package com.dev.l3.configuration;

import com.dev.l3.entity.Role;
import com.dev.l3.entity.User;
import com.dev.l3.repository.RoleRepository;
import com.dev.l3.repository.UserRepository;
import com.dev.l3.utils.enums.RoleEnum;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AppInit implements ApplicationRunner {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.findByUsername("admin").isEmpty()) {
            var role = roleRepository.findByRoleName(RoleEnum.ROLE_LEADER.name())
                    .orElseGet(() -> roleRepository.save(Role.builder()
                            .roleName(RoleEnum.ROLE_LEADER.name())
                            .build()));
            userRepository.save(User.builder()
                    .name("Administrator")
                    .code("LD01")
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .roles(new HashSet<>(Set.of(role)))
                    .build());
            log.warn("The default administrator account has been created: User Name : admin, Password : admin");
            log.warn("Please change the password after logging in for the first time");
        }
    }
}
