package com.jlefler.placepicker;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne
    private User user;

    @ManyToMany
    private Collection<Place> places;

    public List() {
    }

    public List(String name, User user) {
        this.name = name;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Place place) {
        places.add(place);
    }
}
