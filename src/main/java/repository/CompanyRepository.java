package repository;

import entities.Company;

import java.util.Set;

public interface CompanyRepository {
    Company getById(Long id);
    Set<Company> getAll();
    Company save(Company passenger);
    Company update(Company passenger);
    void delete(Long companyId);

}
