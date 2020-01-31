package com.example.helloblog.service;

import com.example.helloblog.entity.Message;
import com.example.helloblog.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Message findById(int id) {
        Optional<Message> message = messageRepository.findById(id);
        if (!message.isPresent()) {
            throw new RuntimeException("Did not find message if - " + id);
        }
        return message.get();
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }

    @Override
    public void deleteById(int id) {
        messageRepository.deleteById(id);
    }
}
