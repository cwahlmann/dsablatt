package de.dreierschach.dsablatt.heldendokument.components;

import com.vaadin.flow.component.ItemLabelGenerator;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class LabeledComboBox<T> extends HorizontalLayout {
    private final NativeLabel label;
    private final ComboBox<T> field;

    public LabeledComboBox(String caption, String tooltip) {
        this.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        field = new ComboBox<>();
        field.setTooltipText(tooltip);
        label = new NativeLabel( caption);
        this.add(label);
        this.addAndExpand(this.field);
        this.setPadding(false);
        this.setSpacing(false);
    }

    public LabeledComboBox<T> withItemLabelGenerator(ItemLabelGenerator<T> itemLabelGenerator) {
        field.setItemLabelGenerator(itemLabelGenerator);
        return this;
    }

    public LabeledComboBox<T> withCaptionWidth(float width, Unit unit) {
        label.setWidth(width, unit);
        return this;
    }

    public LabeledComboBox<T> withValue(T value) {
        field.setValue(value);
        return this;
    }

    public LabeledComboBox<T> withWidth(float width, Unit unit) {
        this.setWidth(width, unit);
        return this;
    }

    public T getValue() {
        return field.getValue();
    }

    public LabeledComboBox<T> withItems(T... items) {
        field.setItems(items);
        return this;
    }

    public ComboBox<T> getField() {
        return field;
    }
}
