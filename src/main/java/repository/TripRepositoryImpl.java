package repository;

import entities.Passenger;
import entities.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Service.Configure.getSessionFactory;

public class TripRepositoryImpl implements TripRepository {
    @Override
    public Trip getById(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Trip trip = session.find(Trip.class, id);

        transaction.commit();
        session.close();

        return trip;
    }

    @Override
    public Set<Trip> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("select t from Trip t", Trip.class);
        Set<Trip> tripSet = new HashSet<>();

        transaction.commit();
        session.close();

        return tripSet;
    }

    @Override
    public Trip save(Trip trip) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(trip);

        transaction.commit();
        session.close();
        return trip;
    }

    @Override
    public Trip update(Trip trip) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(trip);

        transaction.commit();
        session.close();

        return trip;
    }

    @Override
    public void delete(Long tripId) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Trip t where t.id = :id")
                .setParameter("id", tripId)
                .executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<Trip> getTripsFrom(String city) {
        List<Trip> trips;
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        trips = session.createQuery("select t from Trip t where t.townFrom = :town_from", Trip.class)
                .setParameter("town_from", city)
                .getResultList();
        transaction.commit();
        session.close();

        return trips;
    }

    @Override
    public List<Trip> getTripsTo(String city) {
        List<Trip> trips;
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        trips = session.createQuery("select t from Trip t where t.townTo = :town_to", Trip.class)
                .setParameter("town_to", city)
                .getResultList();
        transaction.commit();
        session.close();

        return trips;
    }
}
