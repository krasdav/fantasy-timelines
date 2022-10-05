package com.github.fantasytimelines.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Timeline {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String world;
    private String description;

    @OneToMany(mappedBy = "timeline",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    public Timeline(String name, String world, String description) {
        this.name = name;
        this.world = world;
        this.description = description;
    }

    public void addEvent(Event... events){
        this.events.addAll(List.of(events));
    }

}
