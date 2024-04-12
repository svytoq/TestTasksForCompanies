package org.example.repositories;

import org.example.models.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    List<Message> findByTopicId(int topicId);


    @Modifying
    @Query("update Message m set m.text = ?1 where m.id = ?2")
    boolean updateMessageById(String text, int messageId);

}
