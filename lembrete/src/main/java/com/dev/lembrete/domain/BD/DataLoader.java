/*package com.dev.lembrete.domain.BD;

import com.dev.lembrete.domain.Role;
import com.dev.lembrete.domain.User;
import com.dev.lembrete.repository.RoleRepository;
import com.dev.lembrete.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner
{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception
    {
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role roleAdmin = roleRepository.findByRole("ADMIN");
        Role roleUser = roleRepository.findByRole("USER");

        User userAdmin = new User("pedro1", passwordEncoder.encode("123"), "pedrotest@mail.com", true);
        User uUser = new User("pedro2", passwordEncoder.encode("1234"), "pedrotest@mail.com", true);
        userAdmin.setRoles(Arrays.asList(roleAdmin, roleUser));
        uUser.setRoles(Arrays.asList(roleUser));

        userRepository.save(userAdmin);
        userRepository.save(uUser);
    }
}
*/
/*
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.enable = enable;
        */
