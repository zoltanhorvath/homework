
package hu.horvathzoltan.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(name = "getGuestBookEntriesForPark", query = "select g from GuestBook g where g.park = :park and g.visitor = :visitor")
public class GuestBook implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Calendar entryDate;

    @Basic
    @NotNull
    @Size(min = 1)
    private String text;

    @OneToOne(targetEntity = Park.class)
    private Park park;

    @OneToOne(targetEntity = Visitor.class)
    private Visitor visitor;

    public GuestBook() {
        //No-arg constructor
    }

    public GuestBook(Calendar entryDate, String text, Park park, Visitor visitor) {
        this.entryDate = entryDate;
        this.text = text;
        this.park = park;
        this.visitor = visitor;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Calendar entryDate) {
        this.entryDate = entryDate;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Park getPark() {
        return this.park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public Visitor getVisitor() {
        return this.visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
