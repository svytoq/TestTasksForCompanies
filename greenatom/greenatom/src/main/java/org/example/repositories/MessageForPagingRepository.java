package org.example.repositories;

import org.example.models.Message;
import org.example.models.MessageForPaging;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageForPagingRepository extends JpaRepository<MessageForPaging,Integer> {
    @Query("SELECT m FROM MessageForPaging m WHERE m.topic_id = %:topicId%")
    Page<MessageForPaging> findAllByTopicIdJpql(@Param("topicId") int topicId, Pageable pageable);
}
