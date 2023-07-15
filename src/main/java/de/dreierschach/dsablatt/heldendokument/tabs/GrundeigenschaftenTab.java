package de.dreierschach.dsablatt.heldendokument.tabs;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import de.dreierschach.dsablatt.heldendokument.components.*;
import de.dreierschach.dsalib.model.types.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static de.dreierschach.dsablatt.heldendokument.components.ComponentFactory.*;


public class GrundeigenschaftenTab extends VerticalLayout {

    //--
    LabeledComboBox<Geschlecht> geschlechtField;
    LabeledTextField geborenField;
    LabeledTextField groesseField;
    LabeledTextField gewichtField;
    LabeledComboBox<Haarfarbe> haarfarbelField;
    LabeledComboBox<Augenfarbe> augenfarbeField;
    LabeledTextArea aussehenField;

    LabeledComboBox<Stand> standField;
    LabeledComboBox<Titel> titelField;
    LabeledTextField sozialstatusField;
    LabeledTextArea hintergrundField;

    //--
    com.vaadin.flow.component.textfield.TextArea vorteileNachteileField;

    //--
    Map<Eigenschaft, EigenschaftPanel> eigenschaftPanels = new HashMap<>();
    Map<Basiswert, BasiswertPanel> basiswertPanels = new HashMap<>();

    //--
    LabeledTextField apGesamtField;
    LabeledTextField apEingesetztField;
    LabeledTextField apGuthabenField;

    public GrundeigenschaftenTab() {
        setWidth(100, Unit.PERCENTAGE);
        createAussehenGesellschaftPanel();
        createVorteileNachteilePanel();
        createEigenschaftenComponent();
    }

    private void createAussehenGesellschaftPanel() {
        geschlechtField = new LabeledComboBox<Geschlecht>("Geschlecht:", "Geschlecht des Helden")
                .withItems(Geschlecht.values())
                .withCaptionWidth(8, Unit.EM)
                .withItemLabelGenerator(Geschlecht::getBezeichnung)
                .withWidth(100, Unit.PERCENTAGE);
        geborenField = new LabeledTextField("geboren:", "Geburtsdatum des Helden")
                .withCaptionWidth(8, Unit.EM)
                .withWidth(100, Unit.PERCENTAGE);
        groesseField = new LabeledTextField("Größe:", "Körpergröße des Helden in cm")
                .withCaptionWidth(8, Unit.EM)
                .withWidth(100, Unit.PERCENTAGE);
        gewichtField = new LabeledTextField("Gewicht:", "Gewicht des Helden in Stein")
                .withCaptionWidth(8, Unit.EM)
                .withWidth(100, Unit.PERCENTAGE);
        haarfarbelField = new LabeledComboBox<Haarfarbe>("Haarfarbe:", "Haarfarbe des Helden")
                .withItems(Haarfarbe.values())
                .withCaptionWidth(8, Unit.EM)
                .withItemLabelGenerator(Haarfarbe::getBezeichnung)
                .withWidth(100, Unit.PERCENTAGE);
        augenfarbeField = new LabeledComboBox<Augenfarbe>("Augenfarbe:", "Augenfarbe des Helden")
                .withItems(Augenfarbe.values())
                .withCaptionWidth(8, Unit.EM)
                .withItemLabelGenerator(Augenfarbe::getBezeichnung)
                .withWidth(100, Unit.PERCENTAGE);
        aussehenField = new LabeledTextArea("Aussehen:", "Aussehen des Helden")
                .withCaptionWidth(8, Unit.EM)
                .withWidth(100, Unit.PERCENTAGE);
        aussehenField.getField().setHeight(6, Unit.EM);

        standField = new LabeledComboBox<Stand>("Stand:", "Stand des Heldern")
                .withItems(Stand.values())
                .withCaptionWidth(8, Unit.EM)
                .withItemLabelGenerator(Stand::getBezeichnung)
                .withWidth(100, Unit.PERCENTAGE);
        titelField = new LabeledComboBox<Titel>("Titel:", "Titel des Heldern")
                .withItems(Titel.values())
                .withCaptionWidth(8, Unit.EM)
                .withItemLabelGenerator(Titel::getBezeichnung)
                .withWidth(100, Unit.PERCENTAGE);
        sozialstatusField = new LabeledTextField("Sozialstatus:", "Sozialstatus des Helden")
                .withCaptionWidth(8, Unit.EM)
                .withWidth(100, Unit.PERCENTAGE);
        hintergrundField = new LabeledTextArea("Hintergrund:", "Familie / Herkunft / Hintergrund des Helden")
                .withCaptionWidth(8, Unit.EM)
                .withWidth(100, Unit.PERCENTAGE);
        hintergrundField.getField().setHeight(15, Unit.EM);

        var left = new VerticalLayout(geschlechtField, geborenField, groesseField, gewichtField, haarfarbelField, augenfarbeField, aussehenField);
        left.setWidth(100, Unit.PERCENTAGE);
        left.setPadding(false);
        left.setSpacing(false);
        var right = new VerticalLayout(standField, titelField, sozialstatusField);
        right.addAndExpand(hintergrundField);
        right.setWidth(100, Unit.PERCENTAGE);
        right.setPadding(false);
        right.setSpacing(false);
        add(responsiveLayout(2, left, right));
    }

    private void createVorteileNachteilePanel() {
        add(centeredLabel("Vorteile & Nachteile"));
        vorteileNachteileField = new com.vaadin.flow.component.textfield.TextArea();
        vorteileNachteileField.setWidth(100, Unit.PERCENTAGE);
        vorteileNachteileField.setHeight(8, Unit.EM);
        add(vorteileNachteileField);
    }

    private void createEigenschaftenComponent() {
        var eigenschaftenPanel = new VerticalLayout();
        eigenschaftenPanel.setWidth(100, Unit.PERCENTAGE);
        eigenschaftenPanel.setSpacing(false);
        eigenschaftenPanel.setSpacing(false);
        var eigenschaftenHeader = new HorizontalLayout();
        eigenschaftenHeader.addAndExpand(new Span());
        eigenschaftenHeader.add(label("Modifikator", 5, Unit.EM));
        eigenschaftenHeader.add(label("Start", 5, Unit.EM));
        eigenschaftenHeader.add(label("Aktuell", 5, Unit.EM));
        eigenschaftenHeader.setWidth(100, Unit.PERCENTAGE);
        eigenschaftenPanel.add(eigenschaftenHeader);

        Arrays.stream(Eigenschaft.values()).forEach(eigenschaft -> {
            var panel = new EigenschaftPanel(eigenschaft);
            eigenschaftPanels.put(eigenschaft, panel);
            eigenschaftenPanel.add(panel);
        });

        var basiswertePanel = new VerticalLayout();
        basiswertePanel.setWidth(100, Unit.PERCENTAGE);
        basiswertePanel.setSpacing(false);
        basiswertePanel.setSpacing(false);
        var basiswerteHeader = new HorizontalLayout();
        basiswerteHeader.addAndExpand(new Span());
        basiswerteHeader.add(label("Modifikator", 5, Unit.EM));
        basiswerteHeader.add(label("Start", 5, Unit.EM));
        basiswerteHeader.add(label("Aktuell", 5, Unit.EM));
        basiswerteHeader.add(label("Zugekauft", 5, Unit.EM));
        basiswerteHeader.add(label("Max. Zug.", 5, Unit.EM));
        basiswerteHeader.setWidth(100, Unit.PERCENTAGE);
        basiswertePanel.add(basiswerteHeader);

        Arrays.stream(Basiswert.values()).forEach(basiswert -> {
            var panel = new BasiswertPanel(basiswert);
            basiswertPanels.put(basiswert, panel);
            basiswertePanel.add(panel);
        });

        add(centeredLabel("Eigenschaften & Basiswerte"));
        add(responsiveLayout(2, eigenschaftenPanel, basiswertePanel));
    }

    public LabeledComboBox<Geschlecht> getGeschlechtField() {
        return geschlechtField;
    }

    public LabeledTextField getGeborenField() {
        return geborenField;
    }

    public LabeledTextField getGroesseField() {
        return groesseField;
    }

    public LabeledTextField getGewichtField() {
        return gewichtField;
    }

    public LabeledComboBox<Haarfarbe> getHaarfarbelField() {
        return haarfarbelField;
    }

    public LabeledComboBox<Augenfarbe> getAugenfarbeField() {
        return augenfarbeField;
    }

    public LabeledTextArea getAussehenField() {
        return aussehenField;
    }

    public LabeledComboBox<Stand> getStandField() {
        return standField;
    }

    public LabeledComboBox<Titel> getTitelField() {
        return titelField;
    }

    public LabeledTextField getSozialstatusField() {
        return sozialstatusField;
    }

    public LabeledTextArea getHintergrundField() {
        return hintergrundField;
    }

    public TextArea getVorteileNachteileField() {
        return vorteileNachteileField;
    }

    public EigenschaftPanel getEigenschaftPanel(Eigenschaft eigenschaft) {
        return eigenschaftPanels.get(eigenschaft);
    }

    public BasiswertPanel getBasiswertPanel(Basiswert basiswert) {
        return basiswertPanels.get(basiswert);
    }

    public LabeledTextField getApGesamtField() {
        return apGesamtField;
    }

    public LabeledTextField getApEingesetztField() {
        return apEingesetztField;
    }

    public LabeledTextField getApGuthabenField() {
        return apGuthabenField;
    }
}
