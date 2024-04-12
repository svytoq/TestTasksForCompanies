package org.example.service.interfaces;

import org.example.models.Administrator;

import java.util.List;

public interface AdministratorService {
    boolean saveAdmin(Administrator administrator);
    List<Administrator> list();
    boolean deleteTopic(int topicId);
}
