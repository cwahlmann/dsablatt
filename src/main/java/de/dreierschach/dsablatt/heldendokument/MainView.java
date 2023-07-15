package de.dreierschach.dsablatt.heldendokument;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Route;
import de.dreierschach.dsablatt.heldendokument.components.*;
import de.dreierschach.dsablatt.heldendokument.tabs.FertigkeitenTab;
import de.dreierschach.dsablatt.heldendokument.tabs.GrundeigenschaftenTab;
import de.dreierschach.dsalib.model.types.Kultur;
import de.dreierschach.dsalib.model.types.Profession;
import de.dreierschach.dsalib.model.types.Rasse;

@Route("") // map view to the root
class MainView extends View<MainPresenter> {

    LabeledTextField nameField;
    LabeledComboBox<Rasse> rasseField;
    LabeledComboBox<Kultur> kulturField;
    LabeledComboBox<Profession> professionField;
    LabeledTextField generierungspunkteField;
    LabeledTextArea modifikationenField;
    GrundeigenschaftenTab grundeigenschaftenTab;
    FertigkeitenTab fertigkeitenTab;

    @Override
    public Component createContent() {
        // this.getThemeList().set(Lumo.DARK, true);
        var c = new VerticalLayout();
        createTitle(c);
        createIdentitaetPanel(c);
        createTabSheet(c);
        return c;
    }

    private void createTitle(VerticalLayout c) {
        var docTitleField = new H1("Heldendokument");
        var h = new VerticalLayout(docTitleField);
        h.setWidth(100, Unit.PERCENTAGE);
        h.add(docTitleField);
        h.setHorizontalComponentAlignment(Alignment.CENTER, docTitleField);
        c.add(h);
    }

    private void createIdentitaetPanel(VerticalLayout c) {
        nameField = new LabeledTextField("Name:", "Name des Helden")
                .withCaptionWidth(8, Unit.EM)
                .withWidth(100, Unit.PERCENTAGE);
        rasseField = new LabeledComboBox<Rasse>("Rasse:", "Rasse des Helden")
                .withCaptionWidth(8, Unit.EM)
                .withItemLabelGenerator(Rasse::getBezeichnung)
                .withItems(Rasse.values());
        rasseField.setWidth(100, Unit.PERCENTAGE);
        kulturField = new LabeledComboBox<Kultur>("Kultur:", "Kultur des Helden")
                .withCaptionWidth(8, Unit.EM)
                .withItemLabelGenerator(Kultur::getBezeichnung)
                .withItems(Kultur.values());
        kulturField.setWidth(100, Unit.PERCENTAGE);
        professionField = new LabeledComboBox<Profession>("Profession:", "Profession des Helden")
                .withCaptionWidth(8, Unit.EM)
                .withItemLabelGenerator(Profession::getBezeichnung)
                .withItems(Profession.values());
        professionField.setWidth(100, Unit.PERCENTAGE);
        generierungspunkteField = new LabeledTextField("GP-Basis:", "Basis-Genererierungspunkte")
                .withCaptionWidth(8, Unit.EM)
                .withWidth(100, Unit.PERCENTAGE);

        modifikationenField = new LabeledTextArea("Modifikationen:", "Modifikationen")
                .withCaptionWidth(8, Unit.EM)
                .withWidth(100, Unit.PERCENTAGE);
        modifikationenField.getField().setHeight(8, Unit.EM);
        var left = new VerticalLayout(nameField, rasseField, kulturField, professionField);
        left.setSpacing(false);
        left.setPadding(false);
        left.setWidth(100, Unit.PERCENTAGE);
        var right = new VerticalLayout(generierungspunkteField);
        right.setSpacing(false);
        right.setPadding(false);
        right.addAndExpand(modifikationenField);
        right.setSizeFull();
        c.add(ComponentFactory.responsiveLayout(2, left, right));
    }

    private void createTabSheet(VerticalLayout c) {
        var tabsheet = new TabSheet();
        setWidth(100, Unit.PERCENTAGE);
        grundeigenschaftenTab = new GrundeigenschaftenTab();
        tabsheet.add("Grundeigenschaften", grundeigenschaftenTab);
        fertigkeitenTab = new FertigkeitenTab();
        tabsheet.add("Fertigkeiten", fertigkeitenTab);
        tabsheet.setWidth(100, Unit.PERCENTAGE);
        c.addAndExpand(tabsheet);
    }
}
