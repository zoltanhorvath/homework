package hu.horvathzoltan.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "UPHOLSTERY_FABRICS")
public class UpholsteryFabrics extends Product {

    double width;

    public UpholsteryFabrics() {
        // Default Constructor
    }

    public UpholsteryFabrics(String name, double price, Company company, double width) {
        super(name, price, company);
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "UpholsteryFabrics{" +
                "width=" + width +
                '}';
    }
}
