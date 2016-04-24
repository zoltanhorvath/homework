package hu.horvathzoltan.entity;

import javax.persistence.*;
import java.util.List;
import java.io.Serializable;

@Entity
public class Company implements Serializable{

    @Id
    @GeneratedValue()
    private long id;

    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.PERSIST)
    private List<Contact> contacts;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "company", cascade = CascadeType.PERSIST)
    private List<Product> products;

    public Company() {
        // Default Constructor
    }

    public Company(String name, List<Contact> contacts, Address address) {
        this.name = name;
        this.contacts = contacts;
        this.address = address;
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

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contacts=" + contacts +
                ", address=" + address +
                ", products=" + products +
                '}';
    }
}
