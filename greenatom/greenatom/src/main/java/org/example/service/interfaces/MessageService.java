package org.example.service.interfaces;


import org.example.models.Message;
import org.example.models.MessageForPaging;
import org.example.models.TopicWithMessage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageService {
    List<Message> findByTopicId(int topicId);
    Page<MessageForPaging> findByTopicId(int topicId, Integer offset, Integer limit);
    Message findById(int id);
    void createMessage(Message message);
    void createMessages(TopicWithMessage topic, List<Message> messages);
    boolean updateMessage(int topicId, Message message);
    boolean deleteMessage(int messageId);

    boolean messageExistById(int messageId);
}
