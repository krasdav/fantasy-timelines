package com.github.fantasytimelines.service;

import com.github.fantasytimelines.model.*;
import com.github.fantasytimelines.model.enums.EventAttributeType;
import com.github.fantasytimelines.model.enums.EventType;
import com.github.fantasytimelines.model.enums.TimelineAttributeType;
import com.github.fantasytimelines.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TimelineRepository timelineRepository;
    private final EventRepository eventRepository;
    private final EventAttributeRepository eventAttributeRepository;
    private final UserRepository userRepository;
    private final TimelineAttributeRepository timelineAttributeRepository;


    public Timeline findBestTimeline(){
        return timelineRepository.findAll().get(0);
    }


    public List<Timeline> getAllTimelines(){
        return timelineRepository.findAll();
    }

    public User createUser(String name, String password){
        User user = new User(name,password);
        return userRepository.save(user);
    }

    public Timeline createTimeline(String name, String world, String description, User user){
        Timeline timeline = new Timeline(name,world,description,user);
        return timelineRepository.save(timeline);
    }

    public Event createEvent(String name, String description, String label, Timeline timeline, String pointInTime, EventType eventType){
        Event event = new Event(name,description,label,timeline,pointInTime,eventType);
        return eventRepository.save(event);
    }

    public TimelineAttribute createTimelineAttribute(TimelineAttributeType type, String value, String element,Timeline timeline){
        TimelineAttribute timelineAttribute = new TimelineAttribute(type,value,element,timeline);
        return timelineAttributeRepository.save(timelineAttribute);
    }

    public EventAttribute createTimelineAttribute(EventAttributeType type, String value, String element, Event event){
        EventAttribute eventAttribute = new EventAttribute(type,value,element,event);
        return eventAttributeRepository.save(eventAttribute);
    }



}
