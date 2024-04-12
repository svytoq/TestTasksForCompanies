package org.example.controller;


import jakarta.persistence.EntityNotFoundException;
import org.example.models.Message;
import org.example.models.Topic;
import org.example.models.TopicWithMessage;
import org.example.service.interfaces.MessageService;
import org.example.service.interfaces.TopicService;
import org.example.service.interfaces.TopicWithMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {

    private final TopicWithMessageService topicWithMessageService;

    private final TopicService topicService;
    private final MessageService messageService;

    @Autowired
    public AdminController(TopicWithMessageService topicWithMessageService, TopicService topicService, MessageService messageService) {
        this.topicWithMessageService = topicWithMessageService;
        this.topicService = topicService;
        this.messageService = messageService;
    }


    @PutMapping("admin/topic")
    public ResponseEntity<?> updateTopic(@RequestBody TopicWithMessage topicWithMessage){
        Topic foundTopic = topicService.findById(topicWithMessage.getId());
        foundTopic.setName(topicWithMessage.getName());
        topicService.saveTopic(foundTopic);
        return  new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("admin/topic")
    public ResponseEntity<?> deleteTopic(@RequestBody TopicWithMessage topicWithMessage){
        Topic foundTopic = topicService.findById(topicWithMessage.getId());

        topicService.saveTopic(foundTopic);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/admin/topic/{topicId}/message")
    public ResponseEntity<?> updateMessage(@PathVariable(name = "topicId") int topicId, @RequestBody Message message){
        Topic topic = topicService.findById(topicId);
        Message message1 = messageService.findById(message.getId());

        TopicWithMessage topicWithMessage = new TopicWithMessage(topicId, topic.getName(), topic.getCreated(), new ArrayList<Message>());

        message.setTopic(topicWithMessage);
        message.setAuthor(message1.getAuthor());
        message.setCreated(message1.getCreated());
        messageService.createMessage(message);

        return  new ResponseEntity<>(HttpStatus.OK);

    }



    @DeleteMapping("/admin/message/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable(name = "messageId") int messageId){
        final boolean deleted = messageService.deleteMessage(messageId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
