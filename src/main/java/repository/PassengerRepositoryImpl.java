package repository;

import entities.Passenger;
import entities.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Service.Configure.getSessionFactory;

public class PassengerRepositoryImpl implements PassengerRepository {
    @Override
    public Passenger getById(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Passenger passenger = session.find(Passenger.class, id);

        transaction.commit();
        session.close();

        return passenger;
    }

    @Override
    public Set<Passenger> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("select p from Passenger p", Passenger.class);
        Set<Passenger> passengerSet = new HashSet<>();

        transaction.commit();
        session.close();

        return passengerSet;
    }

    @Override
    public Passenger save(Passenger passenger) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(passenger);

        transaction.commit();
        session.close();
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(passenger);

        transaction.commit();
        session.close();

        return passenger;
    }

    @Override
    public void delete(Long passengerId) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Passenger p where p.id = :id")
                .setParameter("id", passengerId)
                .executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<Passenger> getPassengersOfTrip(Long tripNumber) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<Passenger> passengerList = session.createQuery("select p.name " +
                        "from Passenger p " +
                        "join PassInTrip pit ON p.id = pit.passenger.id " +
                        "join Trip t ON pit.trip.tripNumber = t.tripNumber " +
                        "where t.tripNumber = :tripNumber", Passenger.class)
                .setParameter("tripNumber", tripNumber).getResultList();

        transaction.commit();
        session.close();

        return passengerList;
    }

//    @Override
//    public void registerTrip(Trip trip, Passenger passenger) {
//        Session session = getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//
//
//        transaction.commit();
//        session.close();
//    }
//
//    @Override
//    public void cancelTrip(Long passengerId, Long tripNumber) {
//        Session session = getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//
//
//        transaction.commit();
//        session.close();
//    }
}
