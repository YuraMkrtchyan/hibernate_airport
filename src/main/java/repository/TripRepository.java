package repository;

import entities.Trip;

import java.util.List;
import java.util.Set;

public interface TripRepository {
    Trip getById(Long id);
    Set<Trip> getAll();
    Trip save(Trip passenger);
    Trip update(Trip passenger);
    void delete(Long tripId);
    List<Trip> getTripsFrom(String city);
    List<Trip> getTripsTo(String city);

}
