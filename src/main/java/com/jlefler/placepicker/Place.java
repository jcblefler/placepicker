package com.jlefler.placepicker;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String category;

    @ManyToMany
    private Collection<List> lists;

    public Place() {
    }

    public Place(String name, String category, Collection<List> list) {
        this.name = name;
        this.category = category;
        this.lists = list;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Collection<List> getList() {
        return lists;
    }

    public void setList(List list) {
        lists.add(list);
    }
}
