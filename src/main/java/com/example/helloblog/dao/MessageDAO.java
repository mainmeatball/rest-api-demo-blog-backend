package com.example.helloblog.dao;

import com.example.helloblog.entity.Message;

import java.util.List;

public interface MessageDAO {

    public List<Message> findAll();
}
