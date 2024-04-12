package org.example.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "topic")
@AllArgsConstructor
@NoArgsConstructor
public class TopicWithMessage {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "created")
    private String created;

    @JsonManagedReference
    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Message> messageList;

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
        messageList.forEach(message -> message.setTopic(this));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicWithMessage topic = (TopicWithMessage) o;
        return id == topic.id && Objects.equals(name, topic.name) && Objects.equals(created, topic.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, created);
    }


    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
