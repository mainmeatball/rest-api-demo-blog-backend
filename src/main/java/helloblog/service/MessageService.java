package helloblog.service;

import helloblog.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> findAll(int pageNo, int pageSize, String sortBy, String dir);

    Message findById(int id);

    void save(Message message);

    void deleteById(int id);

    List<Message> findByUsername(String username, int pageNo, int pageSize, String sortBy, String dir);

    Message update(Message message, String context);
}
