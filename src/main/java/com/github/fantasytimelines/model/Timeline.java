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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String world;
    private String description;

    @OneToMany(mappedBy = "timeline",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private final List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "timeline",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private final List<TimelineAttribute> attributes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="user_id",nullable = true)
    private User user;

    public Timeline(String name, String world, String description, User user) {
        this.name = name;
        this.world = world;
        this.description = description;
        this.user = user;
    }

    public void addEvent(Event... events){
        this.events.addAll(List.of(events));
    }

    public void addAttribute(TimelineAttribute... attributes){
        this.attributes.addAll(List.of(attributes));
    }

}
