package com.github.fantasytimelines.view;

import com.github.fantasytimelines.model.Timeline;
import com.github.fantasytimelines.service.TestService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route("timelines")
public class TimelinesView extends HorizontalLayout {

    private final TestService testService;

    public TimelinesView(@Autowired TestService testService){
        this.testService = testService;
        Grid<Timeline> grid = new Grid<>(Timeline.class,false);
        grid.addColumn(Timeline::getName).setHeader("Name");
        grid.addColumn(Timeline::getWorld).setHeader("World");
        grid.addColumn(Timeline::getDescription).setHeader("Description");

        List<Timeline> timelineList = testService.getAllTimelines();
        grid.setItems(timelineList);

        grid.addItemClickListener(event -> {
            Timeline timeline = event.getItem();
            Map<String, List<String>> parameters = new HashMap<>();
            parameters.put("World",List.of(timeline.getWorld()));
            UI.getCurrent().navigate("timeline",new QueryParameters(parameters));
        });
        add(grid);
    }
}
