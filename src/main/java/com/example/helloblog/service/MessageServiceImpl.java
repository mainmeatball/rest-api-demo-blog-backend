package com.example.helloblog.service;

import com.example.helloblog.dao.MessageDAO;
import com.example.helloblog.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageDAO messageDAO;

    @Autowired
    public MessageServiceImpl(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    @Transactional
    public List<Message> findAll() {
        return messageDAO.findAll();
    }

    @Override
    @Transactional
    public Message findById(int id) {
        return messageDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Message message) {
        messageDAO.save(message);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        messageDAO.deleteById(id);
    }
}
