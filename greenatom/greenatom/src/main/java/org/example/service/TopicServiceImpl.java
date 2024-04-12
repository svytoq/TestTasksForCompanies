package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.models.Topic;
import org.example.repositories.TopicRepository;
import org.example.service.interfaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }
    public List<Topic> readAllTopic() {
        return topicRepository.findAll();
    }

    @Override
    public Page<Topic> readAllTopic(Integer offset, Integer limit) {
        return topicRepository.findAll(PageRequest.of(offset, limit));
    }
    @Override
    public void saveTopic(Topic topic) {
            topicRepository.save(topic);
    }

    @Override
    public Topic findById(int id) {
        return topicRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
