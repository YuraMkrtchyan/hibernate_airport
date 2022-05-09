package repository;

import entities.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.HashSet;
import java.util.Set;

import static Service.Configure.getSessionFactory;


public class CompanyRepositoryImpl implements CompanyRepository {

    @Override
    public Company getById(Long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Company company = session.find(Company.class, id);

        transaction.commit();
        session.close();

        return company;
    }

    @Override
    public Set<Company> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createQuery("select c from Company c", Company.class);
        Set<Company> companySet = new HashSet<>();

        transaction.commit();
        session.close();

        return companySet;
    }

    @Override
    public Company save(Company company) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(company);

        transaction.commit();
        session.close();
        return company;
    }

    @Override
    public Company update(Company company) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.merge(company);

        transaction.commit();
        session.close();

        return company;
    }

    @Override
    public void delete(Long companyId) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from Company c where c.id = :id")
                .setParameter("id", companyId)
                .executeUpdate();
        transaction.commit();
        session.close();
    }
}
