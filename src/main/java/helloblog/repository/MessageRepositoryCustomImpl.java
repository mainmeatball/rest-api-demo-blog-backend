package helloblog.repository;

import helloblog.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Repository
public class MessageRepositoryCustomImpl implements MessageRepositoryCustom {

    EntityManager entityManager;

    @Autowired
    public MessageRepositoryCustomImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public List<Message> findMessageByUserNameAndTags(String username, Set<String> tags, int pageNo, int pageSize, String sortBy, String dir) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> q = cb.createQuery(Message.class);
        Root<Message> m = q.from(Message.class);

        List<Predicate> predicates = new ArrayList<>();

        if (username != null) {
            predicates.add(cb.equal(m.get("user").get("username"), username));
        }
        if (tags != null) {
            for (String tag: tags) {
                predicates.add(m.join("tags").get("name").in(Collections.singletonList(tag)));
            }
        }
        q.where(cb.and(predicates.toArray(new Predicate[0])));
        q.orderBy(dir.equals("desc")? cb.desc(m.get(sortBy)) : cb.asc(m.get(sortBy)));
        TypedQuery<Message> query = entityManager.createQuery(q);
        query.setFirstResult(pageNo * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }
}
