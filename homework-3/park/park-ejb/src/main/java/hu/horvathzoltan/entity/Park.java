package hu.horvathzoltan.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Park implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Basic
    @NotNull
    @Size(min = 1)
    private String name;

    @Basic
    @Size(min = 1)
    private int capital;

    @Basic
    @Size(min = 1)
    private int ticketPrice;

    @Basic
    @Size(min = 1)
    private int parkSpace;

    @OneToMany(targetEntity = Visitor.class, mappedBy = "park")
    private List<Visitor> visitors;

    @OneToMany(targetEntity = Machine.class, mappedBy = "park", cascade = CascadeType.ALL)
    private List<Machine> machines;

    @OneToOne(targetEntity = Address.class)
    private Address address;

    public Park() {
        //No-arg constructor
    }

    public Park(String name, int capital, int ticketPrice, int parkSpace, Address address) {
        this.name = name;
        this.capital = capital;
        this.ticketPrice = ticketPrice;
        this.parkSpace = parkSpace;
        this.address = address;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapital() {
        return this.capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public int getTicketPrice() {
        return this.ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getParkSpace() {
        return this.parkSpace;
    }

    public void setParkSpace(int parkSpace) {
        this.parkSpace = parkSpace;
    }

    public List<Visitor> getVisitors() {
        return this.visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public List<Machine> getMachines() {
        return this.machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addMachine(Machine machine) {
        machines.add(machine);
    }

    public Machine getMachineById(Long machineId) {

        int i = 0;
        while (i <= machines.size()) {
            if (machines.get(i).getId() == machineId) {
                return machines.get(i);
            }
        }
        return null;
    }

    public void addVisitor(Visitor visitor) {
        visitors.add(visitor);
    }

    @Override
    public String toString() {
        return "Park{" + "id=" + id + ", name=" + name + '}';
    }

}
