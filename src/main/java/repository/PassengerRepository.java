package repository;
import entities.Passenger;
import entities.Trip;

import java.util.List;
import java.util.Set;

public interface PassengerRepository {

    Passenger getById(Long id);
    Set<Passenger> getAll();
    Passenger save(Passenger passenger);
    Passenger update(Passenger passenger);
    void delete(Long passengerId);
    List<Passenger> getPassengersOfTrip(Long tripNumber);
//    void registerTrip(Trip trip, Passenger passenger);
//    void cancelTrip(Long passengerId, Long tripNumber);

}
