package com.aegisep.hibernate;

import com.aegisep.hibernate.vo.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateUtil {

    private static final Logger log = LoggerFactory.getLogger(HibernateUtil.class);

    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            return configuration.buildSessionFactory();
        }
        catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static void main(String [] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Query<Person> query = session.createQuery("From Person where person_id =:id", Person.class);
        query.setParameter("id", Long.parseLong("1"));

        List<Person> empList = query.list();

        log.info("  >>>>>>> empList >>>>>>  " + empList);

        for (Person emp: empList) {
            log.info("  >>>>>> person >>>>>>>  " + emp);
        }
     }
}
