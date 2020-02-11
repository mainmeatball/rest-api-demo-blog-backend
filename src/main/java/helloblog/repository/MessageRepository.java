package helloblog.repository;

import helloblog.entity.Message;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Integer> {

    List<Message> findByUser_Username(String username);
}
