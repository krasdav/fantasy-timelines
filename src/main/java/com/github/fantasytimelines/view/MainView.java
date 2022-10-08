package com.github.fantasytimelines.view;

import com.github.fantasytimelines.model.Event;
import com.github.fantasytimelines.model.Timeline;
import com.github.fantasytimelines.model.enums.EventType;
import com.github.fantasytimelines.service.TestService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "")
public class MainView extends VerticalLayout {

    private final TestService service;

    public MainView(@Autowired TestService service) {
        this.service = service;
        VerticalLayout verticalLayout = new VerticalLayout();
        Chart chart = createChart(service.findBestTimeline());
        Button button = new Button("TestView");
        button.addClickListener(event -> UI.getCurrent().navigate("test-view"));
        verticalLayout.add(chart,button);
        add(verticalLayout);
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

    /*
    *
        List<Timeline> timelines = timelineRepository.findAll();

        Tab witcher2 = new Tab("Witcher World");
        Tab witcher1 = new Tab("World of Witcher");

        witcher1.setSelected(true);

        Tabs tabs = new Tabs(witcher1, witcher2);
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.setHeight("240px");
        tabs.setWidth("240px");

        Div page1 = new Div();
        page1.add(createChart(timelines.get(0)));

        Div page2 = new Div();
        page2.add(createChart(timelines.get(1)));

        Div pages = new Div(page1);

        tabs.addSelectedChangeListener( event ->  {
                    String label = event.getSelectedTab().getLabel();
                    for(Timeline timeline : timelines){
                        if(timeline.getName().equals(label)){
                            pages.removeAll();
                            pages.add(createChart(timeline));
                        }
                    }
                }
        );

        add(pages,tabs);
    *
    *
    * */


}
