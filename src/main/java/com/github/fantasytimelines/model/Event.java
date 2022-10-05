package com.github.fantasytimelines.model;

import com.github.fantasytimelines.model.enums.EventType;
import com.vaadin.flow.component.charts.model.DataSeries;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Event{

    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Column(length=1000)
    private String description;
    private String label;

    private EventType eventType;
    private String pointInTime;

    @ManyToOne
    @JoinColumn(name="timeline_id",nullable = true)
    private Timeline timeline;

    public Event(String name, String description, String label,  Timeline timeline, String pointInTime, EventType eventType) {
        this.name = name;
        this.description = description;
        this.timeline = timeline;
        this.pointInTime = pointInTime;
        this.label = label;
        this.eventType = eventType;
    }

    public Event(Event event,Timeline timeline){
        this.name = event.name;
        this.description = event.description;
        this.timeline = timeline;
        this.pointInTime = event.pointInTime;
        this.label = event.label;
        this.eventType = event.eventType;
    }
}
