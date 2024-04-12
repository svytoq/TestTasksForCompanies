package org.example.service.interfaces;

import org.example.models.Topic;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TopicService {
    List<Topic> readAllTopic();
    Page<Topic> readAllTopic(Integer offset, Integer limit);
    void saveTopic(Topic topic);

    Topic findById(int id);
}
