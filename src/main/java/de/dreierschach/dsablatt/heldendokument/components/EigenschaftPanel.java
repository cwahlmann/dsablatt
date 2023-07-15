package de.dreierschach.dsablatt.heldendokument.components;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import de.dreierschach.dsalib.model.types.Eigenschaft;

public class EigenschaftPanel extends HorizontalLayout {
    private final TextField modifikatorField;
    private final TextField startField;
    private final TextField aktuellTextField;

    public EigenschaftPanel(Eigenschaft eigenschaft) {
        super();

        var bezeichnungLabel = new NativeLabel(eigenschaft.getBezeichnung());

        modifikatorField = new TextField();
        modifikatorField.setTooltipText("Modifikator");
        modifikatorField.setWidth(5, Unit.EM);
        modifikatorField.setReadOnly(true);

        startField = new TextField();
        startField.setTooltipText("Start");
        startField.setWidth(5, Unit.EM);

        aktuellTextField = new TextField();
        aktuellTextField.setTooltipText("Aktuell");
        aktuellTextField.setWidth(5, Unit.EM);
        aktuellTextField.setReadOnly(true);

        addAndExpand(bezeichnungLabel);
        add(modifikatorField, startField, aktuellTextField);
    }

    public TextField getModifikatorField() {
        return modifikatorField;
    }

    public TextField getStartField() {
        return startField;
    }

    public TextField getAktuellField() {
        return aktuellTextField;
    }
}
