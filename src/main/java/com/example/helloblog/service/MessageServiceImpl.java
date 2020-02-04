package com.example.helloblog.service;

import com.example.helloblog.entity.Message;
import com.example.helloblog.repository.MessageRepository;
import com.example.helloblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, UserDetailsServiceImpl userDetailsService) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public List<Message> findAll(int pageNo, int pageSize, String sortBy, String dir) {
        Sort sort = dir.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable paging = PageRequest.of(pageNo, pageSize, sort);
        Page<Message> pagedResult = messageRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return null;
        }
    }

    @Override
    public Message findById(int id) {
        Optional<Message> message = messageRepository.findById(id);
        if (!message.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message with id " + id + " is not found.");
        }
        return message.get();
    }

    @Override
    public void save(Message message) {
        UserDetails user = userDetailsService.getCurrentUser();
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User is unauthorized.");
        }
        message.setUser(userRepository.findByUsername(user.getUsername()));
        messageRepository.save(message);
    }

    @Override
    public void deleteById(int id) {
        messageRepository.deleteById(id);
    }
}