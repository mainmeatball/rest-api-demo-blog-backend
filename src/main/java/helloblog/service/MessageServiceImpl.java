package helloblog.service;

import helloblog.entity.Message;
import helloblog.entity.Tag;
import helloblog.repository.MessageRepository;
import helloblog.repository.TagRepository;
import helloblog.repository.UserRepository;
import helloblog.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
        Sort sort = dir.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable paging = PageRequest.of(pageNo, pageSize, sort);
        List<Message> messages;

        // TODO: use messageRepository's methods to find messages if user and/or tags are provided

        messages = messageRepository.findAll();
        Page<Message> pagedResult = new PageImpl<>(messages, paging, messages.size());
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
        String username = SecurityUtils.getCurrentUsername();
        message.setUser(userRepository.findByUsername(username));
        message.setLocalDateTime(LocalDateTime.now());
        Set<Tag> tags = message.getTags();
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