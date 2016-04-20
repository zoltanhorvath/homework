package hu.horvathzoltan.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getContact",
                query = "select c from Contact c where c.name = :contactName"),
        @NamedQuery(
                name = "getContactByPhoneNumber",
                query = "select c.name from Contact c inner join c.phoneNumbers p where p = :phoneNumber"
        )
})

public class Contact {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PHONE")
    @Column(name = "Phone_number")
    private List<String> phoneNumbers;

    @ManyToOne
    private Company company;

    public Contact() {
        // Default Constructor
    }

    public Contact(String name, Date dateOfBirth, List<String> phoneNumbers) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumbers = phoneNumbers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name +
                ", phone: " + phoneNumbers +
                '}';
    }
}
