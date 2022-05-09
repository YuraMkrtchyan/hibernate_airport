package Service;

import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.CompanyRepositoryImpl;
import repository.PassengerRepositoryImpl;
import repository.TripRepositoryImpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Service.Configure.getSessionFactory;

public class Reader {
    public void companyReader() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:/Users/user/IdeaProjects" +
                        "/Airport Hibernate/src/main/resources/companies.txt")))) {
            String[] strArr;
            String str;
            br.readLine();
            while ((str = br.readLine()) != null) {
                strArr = str.split(",");
                Company company = new Company(strArr[0], Date.valueOf(LocalDate.parse(strArr[1], formatter)));
                session.persist(company);
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        transaction.commit();
        session.close();
    }

    public void passInTripReader() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime dateTime = LocalDateTime.parse("1909-01-01 04:00:00.000", formatter);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:/Users/user/IdeaProjects/" +
                        "Airport Hibernate/src/main/resources/pass_in_trip.txt")))) {

            String[] strArr;
            String str;
            while ((str = br.readLine()) != null) {
                strArr = str.split(",");

                PassengerRepositoryImpl passengerRepo = new PassengerRepositoryImpl();
                Passenger passenger = passengerRepo.getById(Long.parseLong(strArr[1]));

                TripRepositoryImpl tripRepo = new TripRepositoryImpl();
                Trip trip = tripRepo.getById(Long.parseLong(strArr[0]));

                PassInTrip passInTrip = new PassInTrip(passenger, trip, strArr[3],
                        LocalDateTime.parse(strArr[2], formatter));

                session.persist(passInTrip);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
    }

    public void tripReader() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime.parse("1909-01-01 04:00:00.000", formatter);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:/Users/user/IdeaProjects/" +
                        "Airport Hibernate/src/main/resources/trip.txt")))) {
            String[] strArr;
            String str;
            while ((str = br.readLine()) != null) {
                strArr = str.split(",");
                CompanyRepositoryImpl companyRepo = new CompanyRepositoryImpl();
                Company company = companyRepo.getById(Long.parseLong(strArr[1]));

                Trip trip = new Trip(Long.parseLong(strArr[0]), company,
                        strArr[2], strArr[3], strArr[4],
                        LocalDateTime.parse(strArr[5], formatter), LocalDateTime.parse(strArr[6], formatter));
                session.persist(trip);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
    }

    public void passengerAndAddressReader() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:/Users/user/IdeaProjects/" +
                        "Airport Hibernate/src/main/resources/passengers.txt")))) {
            String[] strArr;
            String str;
            br.readLine();
            while ((str = br.readLine()) != null) {
                strArr = str.split(",");
                Address address = new Address(strArr[2], strArr[3]);

                Passenger passenger = new Passenger(strArr[0], strArr[1]);
                passenger.setAddress(address);
                session.persist(passenger);
                session.persist(address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        transaction.commit();
        session.close();
    }
}
