package com.example.demo.service;

import com.example.demo.domain.Message;
import com.example.demo.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final MessageRepository messageRepository;

    public Iterable<Message> findByTag(String filter) {
        Iterable<Message> messageByTag;

        if (filter != null && !filter.isEmpty()) {
            messageByTag = messageRepository.findByTag(filter);
        } else {
            messageByTag = messageRepository.findAll();
        }

        return messageByTag;
    }

    public Iterable<Message> findAll() {
        return messageRepository.findAll();
    }
}
