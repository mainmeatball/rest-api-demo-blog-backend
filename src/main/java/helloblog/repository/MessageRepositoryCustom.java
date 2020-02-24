package helloblog.repository;

import helloblog.entity.Message;

import java.util.List;
import java.util.Set;

public interface MessageRepositoryCustom {
    List<Message> findMessageByUserNameAndTags(String username, Set<String> tags, int pageNo, int pageSize, String sortBy, String dir);
}
