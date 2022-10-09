package com.github.fantasytimelines.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@Route("timeline")
public class TimelineView extends VerticalLayout implements HasUrlParameter<String> {
    @Override
    public void setParameter(BeforeEvent event, @WildcardParameter String timeline) {
        add("Welcome to " + event.getUnknownReroute());
    }
}
