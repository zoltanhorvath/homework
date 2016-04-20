package hu.horvathzoltan.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(
        name = "listOfProductsWhichPriceIsGreaterThan",
        query = "select f.name from Furniture f where f.price > :price union select u.name from UpholsteryFabrics u where u.price > :price"
)

public abstract class Product extends AbstractItem {

    double price;

    @ManyToOne
    Company company;

    public Product() {
        // Default Constructor
    }

    Product(String name, double price, Company company) {
        super(name);
        this.price = price;
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
