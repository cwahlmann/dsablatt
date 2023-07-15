package de.dreierschach.dsablatt.heldendokument.components;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextArea;

public class LabeledTextArea extends HorizontalLayout {
    private final NativeLabel label;
    private final TextArea field;

    public LabeledTextArea(String caption, String tooltip) {
        this.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        field = new TextArea();
        field.setTooltipText(tooltip);
        field.setHeight(100, Unit.PERCENTAGE);
        label = new NativeLabel( caption);
        this.add(label);
        this.addAndExpand(field);
        this.setPadding(false);
        this.setSpacing(false);
    }

    public LabeledTextArea withCaptionWidth(float width, Unit unit) {
        label.setWidth(width, unit);
        return this;
    }

    public LabeledTextArea withValue(String value) {
        field.setValue(value);
        return this;
    }

    public LabeledTextArea withWidth(float width, Unit unit) {
        this.setWidth(width, unit);
        return this;
    }

    public String getValue() {
        return field.getValue();
    }

    public TextArea getField() {
        return field;
    }
}
