package org.example.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.models.User;
import org.example.models.enums.Role;
import org.example.repositories.UserRepository;
import org.example.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(User user) {
        String userName = user.getName();
        if (userRepository.findByName(userName) != null) return false;
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new User with name: {}", userName);
        userRepository.save(user);
        return true;
    }
    @Override
    public List<User> list() {
        return userRepository.findAll();
    }
    @Override
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByName(principal.getName());
    }
    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }
    @Override
    public User getUserById(int id) {
        return userRepository.findUserById(id);
    }
    @Override
    public List<User> activeUsers(){
        return userRepository.findAllActiveUsers();
    }

}
