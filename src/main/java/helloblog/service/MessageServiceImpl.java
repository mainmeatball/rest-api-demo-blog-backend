package helloblog.service;

import helloblog.entity.Message;
import helloblog.entity.Tag;
import helloblog.repository.*;
import helloblog.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private TagRepository tagRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository,
                              UserRepository userRepository,
                              TagRepository tagRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Message> findAll(String username, Set<String> tags, int pageNo, int pageSize, String sortBy, String dir) {
        return messageRepository.findMessageByUserNameAndTags(username, tags, pageNo, pageSize, sortBy, dir);
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
        String username = SecurityUtils.getCurrentUsername();
        message.setUser(userRepository.findByUsername(username));
        message.setLocalDateTime(LocalDateTime.now());
        if (message.getTags() == null) {
            message.setTags(new HashSet<>());
        }
        message.setTags(
                message.getTags()
                        .stream()
                        .map(tag -> {
                            Tag test = tagRepository.findByName(tag.getName());
                            if (test != null) {
                                return test;
                            }
                            tagRepository.save(tag);
                            return tag;
                        })
                        .collect(Collectors.toSet()));
        messageRepository.save(message);
    }

    @Override
    public void deleteById(int id) {
        messageRepository.deleteById(id);
    }

    @Override
    public Message update(Message message, String content) {
        message.setContent(content);
        messageRepository.save(message);
        return message;
    }
}