package com.example.helloblog.service;

import com.example.helloblog.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> findAll();

    Message findById(int id);

    void save(Message message);

    void deleteById(int id);
}
