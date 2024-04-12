package org.example.service;


import jakarta.persistence.EntityNotFoundException;
import org.example.models.Topic;
import org.example.models.TopicWithMessage;
import org.example.repositories.TopicWithMessageRepository;
import org.example.service.interfaces.TopicWithMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TopicWithMessageServiceImpl implements TopicWithMessageService {

    private final TopicWithMessageRepository topicWithMessageRepository;

    @Autowired
    public TopicWithMessageServiceImpl(TopicWithMessageRepository topicWithMessageRepository) {
        this.topicWithMessageRepository = topicWithMessageRepository;
    }

    @Override
    public void createTopic(TopicWithMessage topic) {
        topicWithMessageRepository.save(topic);
    }

    @Override
    public List<TopicWithMessage> readAllTopic() {
        return topicWithMessageRepository.findAll();
    }

    @Override
    public TopicWithMessage readTopic(int id) {
        return topicWithMessageRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public int setTopicWithMessageInfoById(Topic topic) {
        return topicWithMessageRepository.setTopicWithMessageInfoById(topic.getName(), topic.getId());
    }

    @Override
    public boolean topicExistById(int id) {
        return topicWithMessageRepository.existsById(id);
    }

//    @Override
//    public List<Topic> readAllTopicLazy() {
//        return topicWithMessageRepository.findAllLazy();
//    }
//

}
