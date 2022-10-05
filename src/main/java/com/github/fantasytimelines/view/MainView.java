package com.github.fantasytimelines.view;

import com.github.fantasytimelines.model.Event;
import com.github.fantasytimelines.model.Timeline;
import com.github.fantasytimelines.model.enums.EventType;
import com.github.fantasytimelines.repository.EventRepository;
import com.github.fantasytimelines.repository.TimelineRepository;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "main")
public class MainView extends VerticalLayout {

    public MainView(@Autowired TimelineRepository timelineRepository,
                    @Autowired EventRepository eventRepository) {


        H1 heading = new H1("Events");
        List<Timeline> timelines = timelineRepository.findAll();

        Tab witcher2 = new Tab("Witcher World");
        Tab witcher1 = new Tab("World of Witcher");

        witcher1.getElement().addEventListener("click", event -> {
            for(Timeline timeline : timelines){
                if(timeline.getName().equals("World of Witcher")){
                    Chart chart = createChart(timeline);
                    add(chart);
                }
            }
        });

        witcher2.getElement().addEventListener("click", event -> {
            for(Timeline timeline : timelines){
                if(timeline.getName().equals("Witcher World")){
                    Chart chart = createChart(timeline);
                    add(chart);
                }
            }
        });


        Tabs tabs = new Tabs(witcher1, witcher2);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.setHeight("240px");
        tabs.setWidth("240px");

        Chart chart = createChart(timelines.get(0));
        add(chart,tabs);
    }

    public Chart createChart(Timeline timeline){
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
        RangeSeries rangeSeries = new RangeSeries();
        for( Event event : events){
            if(event.getEventType() == EventType.POINTINTIME){
                DataSeriesItemTimeline item = new DataSeriesItemTimeline(Integer.valueOf(event.getPointInTime()), event.getLabel(), event.getName(), event.getDescription());
                series.add(item);
            }else{
                String[] pointInTime = event.getPointInTime().split(";");
                DataSeriesItemTimeline item = new DataSeriesItemTimeline(Integer.valueOf(pointInTime[0]), event.getLabel(), event.getName(), event.getDescription());
                DataSeriesItemTimeline item2 = new DataSeriesItemTimeline(Integer.valueOf(pointInTime[1]), event.getLabel(), event.getName(), event.getDescription());
                rangeSeries.add(item);
                rangeSeries.add(item2);
            }

        }

        configuration.addSeries(series);
        configuration.addSeries(rangeSeries);
        return chart;
    }


}
