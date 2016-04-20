package hu.horvathzoltan.entity;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "findAllFurnitures",
                query="select f from Furniture f"),
        @NamedQuery(
                name = "findDistinctColors",
                query="select distinct f.color from Furniture f")
})

public class Furniture extends Product {

    double width;

    double height;

    double depth;

    @Enumerated(EnumType.STRING)
    FurnitureColor color;

    public Furniture() {
        // Default Constructor
    }

    public Furniture(String name, double price, Company company, double width, double height, double depth, FurnitureColor color) {
        super(name, price, company);
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.color = color;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public FurnitureColor getColor() {
        return color;
    }

    public void setColor(FurnitureColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                " name " + name +
                " price " + price +
                "width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", color=" + color +
                '}';
    }
}
