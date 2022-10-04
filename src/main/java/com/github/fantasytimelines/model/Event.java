package com.github.fantasytimelines.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class Event{

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private int posInTimeline;
    private long pointInTime;

    @ManyToOne
    @JoinColumn(name="timeline_id",nullable = true)
    private Timeline timeline;

    public Event(String name, String description, int positioInTimeline, long pointInTime, Timeline timeline) {
        this.name = name;
        this.description = description;
        this.timeline = timeline;
        this.posInTimeline = positioInTimeline;
        this.pointInTime = pointInTime;
    }
}
