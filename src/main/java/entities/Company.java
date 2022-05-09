package entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "company_name", nullable = false)
    private String CompanyName;

    @Column(name = "founding_date")
    private Date FoundingDate;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Trip> tripSet = new HashSet<>();


    public Company() {
    }

    public Company(int idComp, String companyName, Date foundingDate) {
        id = idComp;
        CompanyName = companyName;
        FoundingDate = foundingDate;
    }

    public Company(String companyName, Date foundingDate) {
        CompanyName = companyName;
        FoundingDate = foundingDate;
    }

    public Long getIdComp() {
        return id;
    }

    public void setIdComp(int idComp) {
        id = idComp;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public Date getFoundingDate() {
        return FoundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        FoundingDate = foundingDate;
    }

    @Override
    public String toString() {
        return "entities.Company{" +
                "IdComp=" + id +
                ", CompanyName='" + CompanyName + '\'' +
                ", FoundingDate=" + FoundingDate +
                '}';
    }
}
