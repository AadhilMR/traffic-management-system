package com.aadhil.central.impl;

import com.aadhil.central.remote.DataPersister;
import com.aadhil.dto.TrafficIntersection;
import com.aadhil.dto.Vehicle;
import com.aadhil.util.HibernateUtil;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

@Stateless
public class DataPersistBean implements DataPersister {
    @Override
    public void save(Vehicle vehicle) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(vehicle);
        transaction.commit();
        session.close();
    }

    @Override
    public void save(TrafficIntersection trafficIntersection) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(trafficIntersection);
        transaction.commit();
        session.close();
    }
}
