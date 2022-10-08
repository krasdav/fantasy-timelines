package com.github.fantasytimelines.model;

import com.github.fantasytimelines.model.enums.TimelineAttributeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class TimelineAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private TimelineAttributeType timelineAttributeType;
    private String value;
    private String element;

    @ManyToOne
    @JoinColumn(name="timeline_id",nullable = true)
    private Timeline timeline;

    public TimelineAttribute(TimelineAttributeType timelineAttributeType, String value, String element, Timeline timeline){
        this.value = value;
        this.element = element;
        this.timelineAttributeType = timelineAttributeType;
        this.timeline = timeline;
    }
}
