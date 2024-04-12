package org.example.service.interfaces;




import org.example.models.Topic;
import org.example.models.TopicWithMessage;

import java.util.List;

public interface TopicWithMessageService {

    void createTopic(TopicWithMessage topic);
    List<TopicWithMessage> readAllTopic();
    TopicWithMessage readTopic(int id);
    int setTopicWithMessageInfoById(Topic topic);

    boolean topicExistById(int id);

//    List<Topic> readAllTopicLazy();
}
