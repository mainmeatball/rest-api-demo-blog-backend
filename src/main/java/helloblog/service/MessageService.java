package helloblog.service;

import helloblog.entity.Message;

import java.util.List;
import java.util.Set;

public interface MessageService {

    List<Message> findAll(String username, Set<String> tags, int pageNo, int pageSize, String sortBy, String dir);

    Message findById(int id);

    void save(Message message);

    void deleteById(int id);

    Message update(Message message, String context);

    Message update(Message message, int likes);
}
