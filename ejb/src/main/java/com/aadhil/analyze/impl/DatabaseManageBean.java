package com.aadhil.analyze.impl;

import com.aadhil.analyze.remote.DatabaseManager;
import com.aadhil.dto.TrafficIntersection;
import com.aadhil.dto.Vehicle;
import com.aadhil.entity.AverageSpeed;
import com.aadhil.util.HibernateUtil;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Singleton;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class DatabaseManageBean implements DatabaseManager {
    @Override
    public void saveAverageSpeed(AverageSpeed averageSpeed) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(averageSpeed);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Vehicle> getVehiclesList(String date) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            return session.createQuery("SELECT v FROM Vehicle v WHERE v.time LIKE :date", Vehicle.class)
                    .setParameter("date", date + "%")
                    .list();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TrafficIntersection> getTrafficIntersectionsList(String date) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            return session.createQuery("SELECT t FROM TrafficIntersection t WHERE time LIKE :date", TrafficIntersection.class)
                    .setParameter("date", date + "%")
                    .list();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
