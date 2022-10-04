package com.github.fantasytimelines;

import com.github.fantasytimelines.model.Event;
import com.github.fantasytimelines.model.Timeline;
import com.github.fantasytimelines.repository.EventRepository;
import com.github.fantasytimelines.repository.TimelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class FantasyTimelinesApplication {

	private final TimelineRepository timelineRepository;
	private final EventRepository eventRepository;

	public static void main(String[] args) {
		SpringApplication.run(FantasyTimelinesApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData() {
		return (args) -> {
			Timeline timeline = new Timeline("New", "Sapkowski", "Witcher");
			Event event1 = new Event("World creation", "buitiful",1,123, timeline);
			Event event2 = new Event("Explosion", "ugyl",2,234, timeline);

			timeline.addEvent(event1,event2);
			timelineRepository.save(timeline);
		};
	}

}
