package com.fieldbooking.server.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "field")
public class Field {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long price;
    private Long capacity;
    @ManyToOne
    @JoinColumn
    private Facility facility;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

}
