package org.example.repositories;



import org.example.models.TopicWithMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicWithMessageRepository extends JpaRepository<TopicWithMessage,Integer> {
    @Modifying
    @Query("update TopicWithMessage t set t.name = ?1 where t.id = ?2")
    int setTopicWithMessageInfoById(String name, int messageId);

//    @Modifying
//    @Query("select t from Topic t")
//    List<Topic> findAllLazy();
}
