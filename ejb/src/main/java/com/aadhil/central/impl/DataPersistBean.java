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
    public List<Vehicle> getVehiclesList(String date) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            String hql = "FROM Vehicle WHERE time LIKE :date";
            Query query = session.createQuery(hql);
            query.setParameter("date", date + "%");

            List<Vehicle> vehicles = query.list();
            session.close();
            return vehicles;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(TrafficIntersection trafficIntersection) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(trafficIntersection);
        transaction.commit();
        session.close();
    }

    @Override
    public List<TrafficIntersection> getTrafficIntersectionsList(String date) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            String hql = "FROM TrafficIntersection WHERE time LIKE :date";
            Query query = session.createQuery(hql);
            query.setParameter("date", date + "%");

            List<TrafficIntersection> trafficIntersections = query.list();
            session.close();
            return trafficIntersections;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
