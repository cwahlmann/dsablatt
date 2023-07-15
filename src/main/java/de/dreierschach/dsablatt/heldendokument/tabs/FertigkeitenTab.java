package de.dreierschach.dsablatt.heldendokument.tabs;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import de.dreierschach.dsablatt.heldendokument.components.GabePanel;
import de.dreierschach.dsablatt.heldendokument.components.KampftechnikPanel;
import de.dreierschach.dsablatt.heldendokument.components.TalentPanel;
import de.dreierschach.dsalib.model.types.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static de.dreierschach.dsablatt.heldendokument.components.ComponentFactory.*;
import static de.dreierschach.dsalib.model.types.TalentKategorie.*;
import static de.dreierschach.dsalib.model.types.TalentTyp.BASIS;


public class FertigkeitenTab extends VerticalLayout {

    //--
    TextArea sonderfertigkeitenAusserKampfField;
    Map<Kampftechnik, KampftechnikPanel> kampftechnikPanels = new HashMap<>();
    VerticalLayout gabenPanels;

    public FertigkeitenTab() {
        setWidth(100, Unit.PERCENTAGE);

        var left = new VerticalLayout();
        left.setPadding(false);
        left.add(createSonderfertigkeitenAusserKampfPanel(),
                createGabenPanel(),
                createKampftechnikenPanel(),
                createTalentePanel(KOERPER),
                createTalentePanel(GESELLSCHAFT)
        );

        var right = new VerticalLayout();
        right.setPadding(false);
        right.add(
                createTalentePanel(NATUR),
                createTalentePanel(WISSEN),
                // TODO: Sprachen und Schriftian
                createTalentePanel(HANDWERK));

        add(responsiveLayout(2, left, right));
    }

    private VerticalLayout createSonderfertigkeitenAusserKampfPanel() {
        var panel = new VerticalLayout();
        panel.setWidth(100, Unit.PERCENTAGE);
        panel.setPadding(false);
        panel.setSpacing(false);
        panel.add(centeredLabel("Sonderfertigkeiten (außer Kampf)"));

        sonderfertigkeitenAusserKampfField = new TextArea();
        sonderfertigkeitenAusserKampfField.setWidth(100, Unit.PERCENTAGE);
        sonderfertigkeitenAusserKampfField.setHeight(8, Unit.EM);
        panel.add(sonderfertigkeitenAusserKampfField);
        return panel;
    }

    private VerticalLayout createGabenPanel() {
        var panel = new VerticalLayout();
        panel.setWidth(100, Unit.PERCENTAGE);
        panel.setPadding(false);
        panel.setSpacing(false);
        panel.add(centeredLabel("Gaben (F)"));
        var header = new HorizontalLayout();
        header.addAndExpand(new Span());
        header.add(label("TaW", 5, Unit.EM));
        header.add(label("", 5, Unit.EM));
        header.setWidth(100, Unit.PERCENTAGE);
        panel.add(header);
        gabenPanels = new VerticalLayout();
        gabenPanels.setPadding(false);
        gabenPanels.setSpacing(false);
        gabenPanels.setWidth(100, Unit.PERCENTAGE);
        gabenPanels.add(new GabePanel());
        panel.add(gabenPanels);
        return panel;
    }

    private void setGaben(Stream<Gabe> gaben) {
        gabenPanels.removeAll();
        gaben.forEach(gabe -> gabenPanels.add(new GabePanel(gabe)));
    }

    private VerticalLayout createKampftechnikenPanel() {
        var kampftechnikenPanel = new VerticalLayout();
        kampftechnikenPanel.setWidth(100, Unit.PERCENTAGE);
        kampftechnikenPanel.setPadding(false);
        kampftechnikenPanel.setSpacing(false);
        kampftechnikenPanel.add(centeredLabel("Kampftechniken"));
        var kampftechnikHeader = new HorizontalLayout();
        kampftechnikHeader.addAndExpand(new Span());
        kampftechnikHeader.add(label("BE", 5, Unit.EM));
        kampftechnikHeader.add(label("AT", 5, Unit.EM));
        kampftechnikHeader.add(label("PA", 5, Unit.EM));
        kampftechnikHeader.add(label("TaW", 5, Unit.EM));
        kampftechnikHeader.add(label("", 5, Unit.EM));
        kampftechnikHeader.setWidth(100, Unit.PERCENTAGE);
        kampftechnikenPanel.add(kampftechnikHeader);

        Arrays.stream(Kampftechnik.values()).forEach(kampftechnik -> {
            var panel = new KampftechnikPanel(kampftechnik);
            kampftechnikPanels.put(kampftechnik, panel);
            kampftechnikenPanel.add(panel);
        });
        return kampftechnikenPanel;
    }

    private VerticalLayout createTalentePanel(TalentKategorie kategorie) {
        var panel = new VerticalLayout();
        panel.setWidth(100, Unit.PERCENTAGE);
        panel.setPadding(false);
        panel.setSpacing(false);
        panel.add(centeredLabel(kategorie.getBezeichnung() + "(" + kategorie.getSteigerungstabelleSpalte().name() + ")"));
        var header = new HorizontalLayout();
        header.addAndExpand(new Span());
        header.add(label("Probe", 7, Unit.EM));
        header.add(label("TaW", 5, Unit.EM));
        header.add(label("", 5, Unit.EM));
        header.setWidth(100, Unit.PERCENTAGE);
        panel.add(header);

        Arrays.stream(Talent.values()).filter(t -> t.getKategorie() == kategorie).forEach(talent ->
                panel.add(new TalentPanel(talent)));
        return panel;
    }

    public KampftechnikPanel getKampftechnikPanel(Kampftechnik kampftechnik) {
        return Optional.ofNullable(kampftechnikPanels.get(kampftechnik)).orElseThrow();
    }
}
