package com.github.fantasytimelines.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("test-view")
public class TestView extends HorizontalLayout {

    public TestView(){
        add(new Button("I am here"));
    }
}
