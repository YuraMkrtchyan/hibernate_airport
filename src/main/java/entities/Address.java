package entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String country;

    @Column
    private String city;

    @OneToMany(mappedBy = "address")
    private Set<Passenger> passengerSet = new HashSet<>();

    public Address() {
    }

    public Address(Long id, String country, String city, Set<Passenger> passengerSet) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.passengerSet = passengerSet;
    }

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Passenger> getPassengerSet() {
        return passengerSet;
    }

    public void setPassengerSet(Set<Passenger> passengerSet) {
        this.passengerSet = passengerSet;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", passengerSet=" + passengerSet +
                '}';
    }
}
