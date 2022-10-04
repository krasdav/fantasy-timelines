package com.github.fantasytimelines.model;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
public class Event {

    @Id
    private long id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name="timeline_id",nullable = true)
    private Timeline timeline;

    public Event(String name, String description, Timeline timeline) {
        this.name = name;
        this.description = description;
        this.timeline = timeline;
    }
}
