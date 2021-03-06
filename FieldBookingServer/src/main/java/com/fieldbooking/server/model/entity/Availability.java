package com.fieldbooking.server.model.entity;

import javax.persistence.*;

@Entity(name = "availability")
public class Availability {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn
    private Field field;
    private String fromDate;
    private String toDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
