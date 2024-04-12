package org.example.controller;

import com.sun.jdi.request.InvalidRequestStateException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.example.dto.NewMessage;
import org.example.dto.NewTopic;
import org.example.models.MessageForPaging;
import org.example.models.Topic;

import org.example.models.Message;

import org.example.models.TopicWithMessage;
import org.example.service.interfaces.MessageService;
import org.example.service.interfaces.TopicService;
import org.example.service.interfaces.TopicWithMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RestController
public class TopicController {

    private final TopicWithMessageService topicWithMessageService;

    private final TopicService topicService;
    private final MessageService messageService;
    @Autowired
    public TopicController(TopicWithMessageService topicWithMessageService, TopicService topicService, MessageService messageService) {
        this.topicWithMessageService = topicWithMessageService;
        this.topicService = topicService;
        this.messageService = messageService;
    }

    //todo Возможно в Post/Put запросах стоит возвращать созданную/изменённую сущность, чтобы сразу после создания, на фронте мы переходили во вкладку с созданным топиком без доп запроса
    @PostMapping("/topic")
    public ResponseEntity<?> createTopic(@RequestBody NewTopic newTopic){
        if (newTopic.newMessageList() == null || newTopic.newMessageList().size() != 1){
            throw new InvalidRequestStateException("New Topic must contain 1 message");
        }
        TopicWithMessage topic = new TopicWithMessage();
        topic.setName(newTopic.name());

        LocalDateTime currentDateTime = java.time.LocalDateTime.now();
        List<Message> messageList= new ArrayList<>();
        NewMessage newMessage = newTopic.newMessageList().get(0);
        Message message = new Message();
        message.setText(newMessage.text());
        message.setAuthor(newMessage.author());
        message.setCreated(currentDateTime.toString());
        messageList.add(message);

        topic.setMessageList(messageList);
        topic.setCreated(currentDateTime.toString());

        topicWithMessageService.createTopic(topic);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/topic")
    public ResponseEntity<Page<Topic>> getAllTopics(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                    @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit){

        //final List<Topic> topics = topicService.readAllTopic();
        final Page<Topic> topics = topicService.readAllTopic(offset, limit);
        return topics != null &&  !topics.isEmpty()
                ? new ResponseEntity<>(topics, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); }

    @GetMapping("/topic/{id}")
    public ResponseEntity<Page<MessageForPaging>> getTopic(@PathVariable(name = "id") int id, @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                  @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit){

        //if topic with this id not exit then throws EntityNotFoundException and return 404 to client
        final TopicWithMessage topic = topicWithMessageService.readTopic(id);

        //final List<Message> messages = messageService.findByTopicId(id);
        final Page<MessageForPaging> messages = messageService.findByTopicId(id, offset, limit);

        return topic != null
                ? new ResponseEntity<>(messages, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); }

    @PutMapping("/topic")
    public ResponseEntity<?> updateTopic(@RequestBody TopicWithMessage topicWithMessage){
        Topic foundTopic = topicService.findById(topicWithMessage.getId());
        foundTopic.setName(topicWithMessage.getName());
        topicService.saveTopic(foundTopic);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/topic/{topicId}/message")
    public ResponseEntity<?> createMessage(@PathVariable(name = "topicId") int topicId, @RequestBody NewMessage newMessage){
        Topic topic = topicService.findById(topicId);
        TopicWithMessage topicWithMessage = new TopicWithMessage(topicId, topic.getName(), topic.getCreated(), new ArrayList<Message>());
        Message message = new Message();
        message.setText(newMessage.text());
        message.setAuthor(newMessage.author());
        message.setTopic(topicWithMessage);
        message.setCreated(java.time.LocalDateTime.now().toString());
        messageService.createMessage(message);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //todo Нормальная Идентификация пользователей
    @PutMapping("/topic/{topicId}/message")
    public ResponseEntity<?> updateMessage(@PathVariable(name = "topicId") int topicId, @RequestBody Message message){
        Topic topic = topicService.findById(topicId);
        Message message1 = messageService.findById(message.getId());

        if(message.getAuthor().equals(message1.getAuthor())) {
            throw new EntityNotFoundException("Неверный пользователь");
        }
        TopicWithMessage topicWithMessage = new TopicWithMessage(topicId, topic.getName(), topic.getCreated(), new ArrayList<Message>());

        message.setTopic(topicWithMessage);
        message.setAuthor(message1.getAuthor());
        message.setCreated(message1.getCreated());
        messageService.createMessage(message);

        return  new ResponseEntity<>(HttpStatus.OK);

    }


    //todo Идентификация пользователей
    @DeleteMapping("/message/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable(name = "messageId") int messageId){
        final boolean deleted = messageService.deleteMessage(messageId);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
