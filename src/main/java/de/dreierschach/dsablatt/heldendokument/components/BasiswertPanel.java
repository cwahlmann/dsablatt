package de.dreierschach.dsablatt.heldendokument.components;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import de.dreierschach.dsalib.model.types.Basiswert;

public class BasiswertPanel extends HorizontalLayout {
    private final TextField modifikatorField;
    private final TextField startField;
    private final TextField aktuellField;
    private final TextField zugekauftField;
    private final TextField maxZugekauftField;


    public BasiswertPanel(Basiswert basiswert) {
        super();

        var bezeichnungLabel = new NativeLabel(basiswert.getBezeichnung());

        modifikatorField = new TextField();
        modifikatorField.setTooltipText("Modifikator");
        modifikatorField.setWidth(5, Unit.EM);
        modifikatorField.setReadOnly(true);

        startField = new TextField();
        startField.setTooltipText(basiswert.getKommentar());
        startField.setWidth(5, Unit.EM);
        startField.setReadOnly(true);

        aktuellField = new TextField();
        aktuellField.setTooltipText("Aktuell");
        aktuellField.setWidth(5, Unit.EM);
        aktuellField.setReadOnly(true);

        zugekauftField = new TextField();
        zugekauftField.setTooltipText("Start");
        zugekauftField.setWidth(5, Unit.EM);

        maxZugekauftField = new TextField();
        maxZugekauftField.setTooltipText("Aktuell");
        maxZugekauftField.setWidth(5, Unit.EM);
        maxZugekauftField.setReadOnly(true);

        addAndExpand(bezeichnungLabel);
        add(modifikatorField, startField, aktuellField, zugekauftField, maxZugekauftField);
    }

    public TextField getModifikatorField() {
        return modifikatorField;
    }

    public TextField getStartField() {
        return startField;
    }

    public TextField getAktuellField() {
        return aktuellField;
    }

    public TextField getZugekauftField() {
        return zugekauftField;
    }

    public TextField getMaxZugekauftField() {
        return maxZugekauftField;
    }
}
