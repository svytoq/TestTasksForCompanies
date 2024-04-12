package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.models.Administrator;
import org.example.repositories.AdministratorRepository;
import org.example.service.interfaces.AdministratorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository administratorRepository;

    @Override
    public boolean saveAdmin(Administrator administrator) {
        try{
            administratorRepository.save(administrator);
        }catch (Exception e){
            return false;
        }
        return true;
    }
@Override
    public List<Administrator> list() {
        return administratorRepository.findAllAdmins();
    }

    @Override
    public boolean deleteTopic(int topicId) {
        administratorRepository.deleteById(topicId);
        return !administratorRepository.existsById(topicId);
    }
}