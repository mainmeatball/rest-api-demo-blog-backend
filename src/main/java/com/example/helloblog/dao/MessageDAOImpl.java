package com.example.helloblog.dao;

import com.example.helloblog.entity.Message;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class MessageDAOImpl implements MessageDAO {

    private EntityManager entityManager;

    @Autowired
    public MessageDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Message> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Message> query = currentSession.createQuery("from message", Message.class);
        return query.getResultList();
    }
}
