package com.example.helloblog.dao;

import com.example.helloblog.entity.Message;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class MessageDAOImpl implements MessageDAO {

    private EntityManager entityManager;

    @Autowired
    public MessageDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Message> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Message> query = currentSession.createQuery("from message", Message.class);
        return query.getResultList();
    }

    @Override
    public Message findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession.get(Message.class, id);
    }

    @Override
    public void save(Message message) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(message);
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Message message = currentSession.get(Message.class, id);
        if (message != null) {
            currentSession.delete(message);
        }
    }
}
