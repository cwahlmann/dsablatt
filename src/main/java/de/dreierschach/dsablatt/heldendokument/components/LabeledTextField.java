package de.dreierschach.dsablatt.heldendokument.components;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class LabeledTextField extends HorizontalLayout {
    private final NativeLabel label;
    private final TextField field;

    public LabeledTextField(String caption, String tooltip) {
        field = new TextField();
        field.setTooltipText(tooltip);
        label = new NativeLabel(caption);
        this.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        this.add(label);
        this.addAndExpand(this.field);
        this.setPadding(false);
        this.setSpacing(false);
    }

    public LabeledTextField withCaptionWidth(float width, Unit unit) {
        label.setWidth(width, unit);
        return this;
    }

    public LabeledTextField withValue(String value) {
        field.setValue(value);
        return this;
    }

    public LabeledTextField withWidth(float width, Unit unit) {
        this.setWidth(width, unit);
        return this;
    }

    public String getValue() {
        return field.getValue();
    }

    public TextField getField() {
        return field;
    }
}
