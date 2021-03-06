package helloblog.controller;

import helloblog.entity.Message;
import helloblog.security.SecurityUtils;
import helloblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public List<Message> showMessages(@RequestParam(required = false) String username,
                                      @RequestParam(required = false) Set<String> tags,
                                      @RequestParam(defaultValue = "100") int pageSize,
                                      @RequestParam(defaultValue = "0") int pageNo,
                                      @RequestParam(defaultValue = "id") String sortBy,
                                      @RequestParam(defaultValue = "asc") String dir) {
        return messageService.findAll(username, tags, pageNo, pageSize, sortBy, dir);
    }

    @GetMapping("/messages/{messageId}")
    public Message getMessage(@PathVariable int messageId) {
        Message message = messageService.findById(messageId);
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message with id " + messageId + " is not found.");
        }
        return message;
    }


    @Secured("USER")
    @PostMapping("/messages")
    public Message addMessage(@RequestBody Message message) {
        messageService.save(message);
        return message;
    }

    @Secured("USER")
    @PutMapping("/messages/{messageId}")
    public Message updateMessage(@PathVariable int messageId, @RequestBody String content) {
        Message message = messageService.findById(messageId);
        if (!SecurityUtils.getCurrentUsername().equals(message.getUser().getUsername())) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Message with id " + messageId + " was posted by another user.");
        }
        messageService.update(message, content);
        return message;
    }

    @Secured("USER")
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<String> deleteMessage(@PathVariable int messageId) {
        Message message = messageService.findById(messageId);
        if (!SecurityUtils.getCurrentUsername().equals(message.getUser().getUsername())) {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Message with id " + messageId + " was posted by another user.");
        }
        messageService.deleteById(messageId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Secured("USER")
    @PutMapping("/messages/{messageId}/upvote")
    public Message upvoteMessage(@PathVariable int messageId) {
        Message message = messageService.findById(messageId);
        message.upvote();
        messageService.update(message, message.getLikes());
        return message;
    }

    @Secured("USER")
    @PutMapping("/messages/{messageId}/downvote")
    public Message dwonvoteMessage(@PathVariable int messageId) {
        Message message = messageService.findById(messageId);
        message.downvote();
        messageService.update(message, message.getLikes());
        return message;
    }
}
