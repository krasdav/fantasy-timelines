package com.github.fantasytimelines;

import com.github.fantasytimelines.model.Event;
import com.github.fantasytimelines.model.Timeline;
import com.github.fantasytimelines.model.enums.EventType;
import com.github.fantasytimelines.repository.EventRepository;
import com.github.fantasytimelines.repository.TimelineRepository;
import lombok.RequiredArgsConstructor;
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
			Timeline timeline1 = new Timeline("New", "World of Witcher", "Andrzej Sapkowski");
			Event event1 = new Event("Dwarves arrive", """
					Dwarves arrive in the lands of Continent,
					the gnomes already have small colonies in Mahakam and Tir Tochair""",
					"2700 BR",timeline1, "-2700" ,EventType.POINTINTIME);
			Event event2 = new Event("Age of Migration", "Aen Seidhe elves arrive on their white ships",
					"2230 BR",timeline1,"-2230",EventType.POINTINTIME);
			Event event4 = new Event("Conjunction of the Spheres", """
					Humans arrive in the world.
					Dauk and Wozgor people settle in the Continent's north.
					Emergence of goddess worship in the form of Melitele, one of the oldest and most widespread systems of beliefs
					Development of a written language based on elven runes and dwarven ideograms.""",
					"230s BR",timeline1,"-230", EventType.POINTINTIME);

			Event event3 = new Event("The Resurrection", "The Resurrection","1",timeline1,"1", EventType.POINTINTIME);
			Event event5 = new Event("Nordic people and Fen Carn", """ 
					The necropolis at Fen Carn is created.
					Nordling people arrive in the north of the Continent,
					human civilizations already present in the other parts of the world (people also settle in the south).
					Known as the "First Landing" or "Landing of the Exiles", this event is very important in human history.
					Humans most likely came ashore at the mouth of the Yaruga and the Pontar Delta.
					Once on land, Jan Bekker was quick to discover and harness the Force found there and humans set up the first settlement.
					It took several hundred years for the elves to notice the growing threat posed by the newcomers""",
					"760s",timeline1,"760", EventType.POINTINTIME);

			timeline1.addEvent(event1,event2,event4,event3,event5);

			Timeline timeline2 = new Timeline("New", "Witcher world", "Andrzej Sapkowski");
			timeline2.addEvent(new Event(event1,timeline2),new Event(event5,timeline2));

			timelineRepository.save(timeline1);
			timelineRepository.save(timeline2);

		};
	}

}
