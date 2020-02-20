package com.example.demo.repository;

import com.example.demo.domain.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Transactional
public interface MessageRepository extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);


    @Query(value = "select * from Message where user_id = ?1", nativeQuery = true)
    List<Message> findAllMessagesByUserId(Long id);

}
