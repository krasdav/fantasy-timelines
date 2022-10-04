package com.github.fantasytimelines.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@NoArgsConstructor
public class Timeline {

    @Id
    private long id;
    private String name;
    private String world;
    private String description;

    @OneToMany(mappedBy = "timeline")
    private List<Event> events;

    public Timeline(String name, String world, String description) {
        this.name = name;
        this.world = world;
        this.description = description;
    }
}
