package com.dao;

import com.dataSets.Client;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

public class ClientsDAO {

    private SessionFactory sessionFactory;

    public ClientsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Client get(long id) throws HibernateException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Client usd = (Client) session.get(Client.class, id);
        transaction.commit();
        session.close();
        return usd;
    }

    public Client get(String name) throws HibernateException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Client.class);
        Client usd = (Client) criteria
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        transaction.commit();
        session.close();
        return usd;
    }
/*
    public long getUserId(String name) throws HibernateException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserDataSet.class);
        UserDataSet usd = (UserDataSet) criteria
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        transaction.commit();
        session.close();
        return usd.getId();
    }

    public long insertUser(String name, String password) throws HibernateException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        long id = (Long) session.save(new Client(name, password));
        transaction.commit();
        session.close();
        return id;
    }
 */
}
