package org.example.service;


import jakarta.persistence.EntityNotFoundException;
import org.example.models.Message;

import org.example.models.MessageForPaging;
import org.example.models.TopicWithMessage;
import org.example.repositories.MessageForPagingRepository;
import org.example.repositories.MessageRepository;
import org.example.service.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final MessageForPagingRepository messageForPagingRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, MessageForPagingRepository messageForPagingRepository) {
        this.messageRepository = messageRepository;
        this.messageForPagingRepository = messageForPagingRepository;
    }




    @Override
    public List<Message> findByTopicId(int topicId) {
        return messageRepository.findByTopicId(topicId);
    }

    @Override
    public Page<MessageForPaging> findByTopicId(int topicId, Integer offset, Integer limit) {
        return messageForPagingRepository.findAllByTopicIdJpql(topicId, PageRequest.of(offset, limit));
    }
    @Override
    public Message findById(int id) {
        return messageRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void createMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void createMessages(TopicWithMessage topic, List<Message> messages) {
        for (Message message: messages){
            message.setTopic(topic);
        }
        messageRepository.saveAll(messages);
    }

    @Override
    public boolean updateMessage(int topicId, Message message) {
        return messageRepository.updateMessageById(message.getText(), message.getId());
    }

    @Override
    public boolean deleteMessage(int messageId) {
            messageRepository.deleteById(messageId);
            return !messageRepository.existsById(messageId);
        }


    @Override
    public boolean messageExistById(int messageId) {
        return messageRepository.existsById(messageId);
    }
}
