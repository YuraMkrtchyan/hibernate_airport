package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pass_in_trip")
public class PassInTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "place")
    private String place;

    @Column(name = "trip_date")
    private LocalDateTime trip_Date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;


    public PassInTrip() {

    }

    public PassInTrip(Passenger passenger, Trip trip, String place, LocalDateTime trip_Date) {
        this.passenger = passenger;
        this.trip = trip;
        this.place = place;
        this.trip_Date = trip_Date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDateTime getTrip_Date() {
        return trip_Date;
    }

    public void setTrip_Date(LocalDateTime trip_Date) {
        this.trip_Date = trip_Date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PassInTrip that = (PassInTrip) o;
        return Objects.equals(id, that.id) && Objects.equals(passenger, that.passenger) && Objects.equals(trip, that.trip) && Objects.equals(place, that.place) && Objects.equals(trip_Date, that.trip_Date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passenger, trip, place, trip_Date);
    }
}