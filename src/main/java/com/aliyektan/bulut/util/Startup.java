package com.aliyektan.bulut.util;

import com.aliyektan.bulut.entity.Role;
import com.aliyektan.bulut.entity.User;
import com.aliyektan.bulut.repository.RoleRepository;
import com.aliyektan.bulut.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Startup {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean("initUser")
    @DependsOn("initRoles")
    public void initializeUsers() {

        Role superAdmin = roleRepository.findByName("SUPERADMIN");

        if (!userRepository.findByEmail("superadmin@bulutcarpark.com").isPresent()) {

            User superAdminUser = new User();
            superAdminUser.setEmail("superadmin@bulutcarpark.com");
            superAdminUser.setPhoneNumber("1111111111");
            superAdminUser.setIdentityNumber("11111111111");
            superAdminUser.setPassword(bCryptPasswordEncoder.encode("123456"));
            superAdminUser.setName("ADMIN");
            superAdminUser.setSurname("SUPER");
            superAdminUser.setRole(superAdmin);

            userRepository.save(superAdminUser);
        }

    }

    @Bean("initRoles")
    public void initializeRoles() {

        Role roleAdmin = new Role("ADMIN");
        Role roleUser = new Role("USER");
        Role roleSuperAdmin = new Role("SUPERADMIN");

        if (roleRepository.findByName("ADMIN") == null)
            roleRepository.save(roleAdmin);

        if (roleRepository.findByName("USER") == null)
            roleRepository.save(roleUser);

        if (roleRepository.findByName("SUPERADMIN") == null)
            roleRepository.save(roleSuperAdmin);


    }

}
