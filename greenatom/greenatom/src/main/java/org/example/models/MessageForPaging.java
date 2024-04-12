package org.example.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

//Caused by: org.hibernate.query.sqm.InterpretationException: Error interpreting query [SELECT m FROM Message m WHERE m.topic_id = :topicId]; this may indicate a semantic (user query) problem or a bug in the parser [SELECT m FROM Message m WHERE m.topic_id = :topicId]
//Caused by: org.hibernate.query.SemanticException: Could not resolve attribute 'topic_id' of 'org.example.models.Message'
//Добавил этот класс, так как доделывал в конце пагинацию, и столкнулся с ошибкой сверху, точно можно было сделать лучше, но был сильно ограничен по времени, чтоб разобраться)))

@Entity
@Table(name = "message")
@AllArgsConstructor
@NoArgsConstructor
public class MessageForPaging {
    @Id
    @Column(name = "id")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private int id;

    @Column(name = "text")
    private String text;

    @Column(name = "author")
    private String author;

    @Column(name = "created")
    private String created;


    @Column(name = "topic_id")
    private int topic_id;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}
