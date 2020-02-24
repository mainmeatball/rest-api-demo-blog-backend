package helloblog.repository;

import helloblog.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer>, MessageRepositoryCustom {
}
