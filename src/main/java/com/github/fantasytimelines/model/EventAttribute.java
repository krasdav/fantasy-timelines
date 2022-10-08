package com.github.fantasytimelines.model;

import com.github.fantasytimelines.model.enums.EventAttributeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class EventAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private EventAttributeType eventAttributeType;
    private String value;
    private String element;

    @ManyToOne
    @JoinColumn(name="event_id",nullable = true)
    private Event event;

    public EventAttribute(EventAttributeType eventAttributeType, String value, String element,Event event) {
        this.eventAttributeType = eventAttributeType;
        this.value = value;
        this.element = element;
        this.event = event;
    }
}
