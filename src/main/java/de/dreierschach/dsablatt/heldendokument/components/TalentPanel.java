package de.dreierschach.dsablatt.heldendokument.components;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import de.dreierschach.dsalib.model.types.Talent;
import de.dreierschach.dsalib.model.types.TalentTyp;

import static de.dreierschach.dsalib.model.types.TalentTyp.BASIS;

public class TalentPanel extends HorizontalLayout {
    private final TextField modifikatorField;
    private final TextField aktuellField;

    public TalentPanel(Talent talent) {
        super();
        setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        var bezeichnungLabel = new NativeLabel((talent.getTyp()== BASIS ? "* " : "") + talent.getBezeichnung());
        bezeichnungLabel.setWidth(10, Unit.EM);
        var probeLabel = new NativeLabel(
                talent.getTalentProbe().toString()
                        + (talent.hasAlternativeTalentprobe() ? " oder " + talent.getAlternativeProbe().toString() : ""));
        probeLabel.setWidth(14, Unit.EM);

        modifikatorField = new TextField();
        modifikatorField.setTooltipText("TaW");
        modifikatorField.setWidth(5, Unit.EM);
        modifikatorField.setReadOnly(true);

        aktuellField = new TextField();
        aktuellField.setTooltipText("TaW");
        aktuellField.setWidth(5, Unit.EM);

        addAndExpand(bezeichnungLabel);
        add(probeLabel, modifikatorField, aktuellField);
    }

    public TextField getModifikatorField() {
        return modifikatorField;
    }

    public TextField getAktuellField() {
        return aktuellField;
    }
}
