package com.example.helloblog.controller;

import com.example.helloblog.entity.Message;
import com.example.helloblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message with id " + messageId + " is not found.");
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
    public ResponseEntity<String> deleteMessage(@PathVariable int messageId) {
        Message message = messageService.findById(messageId);
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message with id " + messageId + " is not found.");
        }
        messageService.deleteById(messageId);
        return ResponseEntity.ok("Message with id " + messageId + " was successfully deleted.");
    }

    @PutMapping("/messages/{messageId}/upvote")
    public Message upvoteMessage(@PathVariable int messageId) {
        Message message = messageService.findById(messageId);
        message.upvote();
        messageService.save(message);
        return message;
    }

    @PutMapping("/messages/{messageId}/downvote")
    public Message dwonvoteMessage(@PathVariable int messageId) {
        Message message = messageService.findById(messageId);
        message.downvote();
        messageService.save(message);
        return message;
    }
}
