package helloblog.repository;

import helloblog.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    // TODO: create method that finds messages with provided tags
}
