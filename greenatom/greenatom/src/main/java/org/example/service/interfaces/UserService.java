package org.example.service.interfaces;

import org.example.models.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    boolean createUser(User user);

    List<User> list();

    User getUserByPrincipal(Principal principal);
    User getUserByName(String name);
    User getUserById(int id);
    List<User> activeUsers();
}
