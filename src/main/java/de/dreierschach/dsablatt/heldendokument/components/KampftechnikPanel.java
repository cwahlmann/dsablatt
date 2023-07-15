package de.dreierschach.dsablatt.heldendokument.components;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import de.dreierschach.dsalib.model.types.Kampftechnik;

import static de.dreierschach.dsalib.model.types.TalentTyp.BASIS;

public class KampftechnikPanel extends HorizontalLayout {
    private final TextField atField;
    private final TextField paField;
    private final TextField modifikatorField;
    private final TextField aktuellField;

    public KampftechnikPanel(Kampftechnik kampftechnik) {
        super();
        setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        var bezeichnungLabel = new NativeLabel((kampftechnik.getTyp()== BASIS ? "* " : "") + kampftechnik.getBezeichnung());
        var steigerungLabel = new NativeLabel(kampftechnik.getSteigerungstabelleSpalte().name());
        steigerungLabel.setWidth(5, Unit.EM);
        var behinderungLabel = new NativeLabel(kampftechnik.getEffektiveBehinderung().toString());
        behinderungLabel.setWidth(5, Unit.EM);

        atField = new TextField();
        atField.setTooltipText("AT");
        atField.setWidth(5, Unit.EM);

        paField = new TextField();
        paField.setTooltipText("PA");
        paField.setWidth(5, Unit.EM);

        modifikatorField = new TextField();
        modifikatorField.setTooltipText("TaW");
        modifikatorField.setWidth(5, Unit.EM);
        modifikatorField.setReadOnly(true);

        aktuellField = new TextField();
        aktuellField.setTooltipText("TaW");
        aktuellField.setWidth(5, Unit.EM);

        addAndExpand(bezeichnungLabel);
        add(steigerungLabel, behinderungLabel, atField, paField, aktuellField, modifikatorField, aktuellField);
    }

    public TextField getModifikatorField() {
        return modifikatorField;
    }

    public TextField getAktuellField() {
        return aktuellField;
    }

    public TextField getAtField() {
        return atField;
    }

    public TextField getPaField() {
        return paField;
    }
}
