package com.example.helloblog.controller;

import com.example.helloblog.entity.Message;
import com.example.helloblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageRestController {

    private MessageService messageService;

    @Autowired
    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value={"/", "/messages"})
    public List<Message> show() {
        return messageService.findAll();
    }

    @GetMapping("/messages/{messageId}")
    public Message getMessage(@PathVariable int messageId) {
        Message message = messageService.findById(messageId);
        if (message == null) {
            throw new RuntimeException("Message id not found " + messageId);
        }
        return message;
    }

    @PostMapping("/messages")
    public Message addMessage(@RequestBody Message message) {
        messageService.save(message);
        return message;
    }

    @PutMapping("/messages")
    public Message updateMessage(@RequestBody Message message) {
        messageService.save(message);
        return message;
    }

    @DeleteMapping("/messages/{messageId}")
    public String deleteMessage(@PathVariable int messageId) {
        Message message = messageService.findById(messageId);
        if (message == null) {
            throw new RuntimeException("Message id not found - " + messageId);
        }
        messageService.deleteById(messageId);
        return "Deleted message with id = " + messageId;
    }
}
