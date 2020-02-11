package helloblog.controller;

import helloblog.dto.RolesDto;
import helloblog.dto.UserDto;
import helloblog.entity.Message;
import helloblog.entity.User;
import helloblog.service.MessageService;
import helloblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private MessageService messageService;

    @Autowired
    public AdminController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    // TODO: add @Secure annotation to restrict access to some endpoints

    @GetMapping("/users")
    public List<User> showUsers(@RequestParam(defaultValue = "0") int pageNo,
                                @RequestParam(defaultValue = "100") int pageSize,
                                @RequestParam(defaultValue = "id") String sortBy,
                                @RequestParam(defaultValue = "asc") String dir) {
        return userService.findAll(pageNo, pageSize, sortBy, dir);
    }

    @GetMapping("/users/{userId}")
    public User showUsers(@PathVariable int userId) {
        return userService.findById(userId);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable int userId, @RequestBody UserDto userDto) {
        User user = userService.findById(userId);
        return userService.update(user, userDto);
    }

    @PostMapping("/users/{userId}/block")
    public User blockUser(@PathVariable int userId) {
        User user = userService.findById(userId);
        userService.block(user);
        return user;
    }

    @PostMapping("/users/{userId}/unblock")
    public User unblockUser(@PathVariable int userId) {
        User user = userService.findById(userId);
        userService.unblock(user);
        return user;
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + userId + " was not found.");
        }
        userService.deleteById(userId);
        return ResponseEntity.ok("User with id " + userId + " was successfully deleted.");
    }

    @PutMapping("/users/{userId}/roles")
    public User updateUserRoles(@PathVariable int userId, @Valid @RequestBody RolesDto rolesDto) {
        User user = userService.findById(userId);
        return userService.updateRoles(user, rolesDto);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<String> deleteMessage(@PathVariable int messageId) {
        Message message = messageService.findById(messageId);
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message with id " + messageId + " was not found.");
        }
        messageService.deleteById(messageId);
        return ResponseEntity.ok("Message with id " + messageId + " was successfully deleted.");
    }
}
