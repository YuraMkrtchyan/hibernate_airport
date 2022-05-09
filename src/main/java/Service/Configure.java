package Service;

import entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Configure {
    private static final Configuration configuration = new Configuration();

    private static final SessionFactory sessionFactory = configuration
            .addAnnotatedClass(Company.class)
            .addAnnotatedClass(Trip.class)
            .addAnnotatedClass(PassInTrip.class)
            .addAnnotatedClass(Passenger.class)
            .addAnnotatedClass(Address.class)
            .buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
