package com.example.helloblog.dao;

import com.example.helloblog.entity.Message;

import java.util.List;

public interface MessageDAO {

    List<Message> findAll();

    Message findById(int id);

    void save(Message message);

    void deleteById(int id);
}
