package de.dreierschach.dsablatt.heldendokument.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.stream.Stream;

public class ComponentFactory {
    private ComponentFactory() {}

    public static FormLayout responsiveLayout(int cols, Component... components) {
        var layout = new FormLayout(components);
        var stepsize = 1920 / cols;
        var steps = Stream.iterate(0, i->i<cols, i->i+1).map(i -> new FormLayout.ResponsiveStep((i*stepsize)+"px", i+1))
                .toArray(i -> new FormLayout.ResponsiveStep[cols]);
        layout.setResponsiveSteps(steps);
        layout.setWidth(100, Unit.PERCENTAGE);
        return layout;
    }

    public static VerticalLayout centeredLabel(String text) {
        var result = new VerticalLayout();
        result.setWidth(100, Unit.PERCENTAGE);
        result.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        result.add(new NativeLabel(text));
        return result;
    }

    public static NativeLabel label(String text, int width, Unit unit) {
        var label = new NativeLabel(text);
        label.setWidth(width, unit);
        return label;
    }
}
