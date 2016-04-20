package hu.horvathzoltan.entity;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractItem {

    @Id
    @GeneratedValue
    long id;

    String name;

    AbstractItem() {
        // Default Constructor
    }

    AbstractItem(String name) {
        this.name = name;
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
}
