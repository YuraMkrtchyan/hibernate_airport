package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "trip_no", nullable = false)
    private Long tripNumber;

    @Column(name = "plane", length = 10)
    private String plane;

    @Column(name = "town_from")
    private String townFrom;

    @Column(name = "town_to")
    private String townTo;

    @Column(name = "time_out")
    private LocalDateTime timeOut;

    @Column(name = "time_in")
    private LocalDateTime timeIn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "trip",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<PassInTrip> passengers = new HashSet<>();

    public Trip() {

    }

    public Trip(long tripNumber, Company company, String plane, String townFrom, String townTo
            , LocalDateTime timeOut, LocalDateTime timeIn) {
        this.tripNumber = tripNumber;
        this.plane = plane;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(Long tripNumber) {
        this.tripNumber = tripNumber;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }

    public LocalDateTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalDateTime timeOut) {
        this.timeOut = timeOut;
    }

    public LocalDateTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalDateTime timeIn) {
        this.timeIn = timeIn;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<PassInTrip> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<PassInTrip> passengers) {
        this.passengers = passengers;
    }
}