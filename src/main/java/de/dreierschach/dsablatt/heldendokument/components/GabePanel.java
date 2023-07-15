package de.dreierschach.dsablatt.heldendokument.components;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import de.dreierschach.dsalib.model.types.Gabe;

public class GabePanel extends HorizontalLayout {
    private final TextField tawStartField;
    private final TextField tawAktuellField;

    public GabePanel() {
        this(null);
    }

    public GabePanel(Gabe gabe) {
        super();
        setDefaultVerticalComponentAlignment(Alignment.BASELINE);
        var bezeichnungLabel = gabe != null ? new NativeLabel(gabe.getBezeichnung()) : new NativeLabel("");
        bezeichnungLabel.setWidth(8, Unit.EM);

        tawStartField = new TextField();
        tawStartField.setTooltipText("TaW (Start)");
        tawStartField.setWidth(5, Unit.EM);

        tawAktuellField = new TextField();
        tawAktuellField.setTooltipText("TaW (Aktuell)");
        tawAktuellField.setWidth(5, Unit.EM);

        addAndExpand(bezeichnungLabel);
        add(tawStartField, tawAktuellField);
    }

    public TextField getTawStartField() {
        return tawStartField;
    }

    public TextField getTawAktuellField() {
        return tawAktuellField;
    }
}
