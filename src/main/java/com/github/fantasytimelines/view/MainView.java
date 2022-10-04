package com.github.fantasytimelines.view;

import com.github.fantasytimelines.model.Event;
import com.github.fantasytimelines.model.Timeline;
import com.github.fantasytimelines.repository.EventRepository;
import com.github.fantasytimelines.repository.TimelineRepository;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "main")
public class MainView extends VerticalLayout {

    public MainView(@Autowired TimelineRepository timelineRepository,
                    @Autowired EventRepository eventRepository) {

        H1 heading = new H1("Events");
        Timeline timeline = timelineRepository.findAll().get(0);
        List<Event> events = timeline.getEvents();

        Chart chart = new Chart(ChartType.TIMELINE);

        Configuration configuration = chart.getConfiguration();
        configuration.setTitle(timeline.getWorld());
        configuration.setSubTitle(timeline.getDescription());
        configuration.getTooltip().setEnabled(true);
        configuration.getxAxis().setVisible(false);
        configuration.getxAxis().setType(AxisType.DATETIME);
        configuration.getyAxis().setVisible(false);

        DataSeries series = new DataSeries();
        for( Event event : events){
            series.add(new DataSeriesItemTimeline(event.getPointInTime(),event.getName(),event.getName(),event.getDescription()));
        }

        configuration.addSeries(series);

        add(chart);
    }
}
