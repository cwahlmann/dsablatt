package de.dreierschach.dsablatt.heldendokument;

import com.vaadin.flow.data.binder.Binder;
import de.dreierschach.dsablatt.heldendokument.components.Presenter;
import de.dreierschach.dsalib.model.Heldendokument;
import de.dreierschach.dsalib.model.types.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;

import static de.dreierschach.dsablatt.heldendokument.utility.BinderUtil.*;

@Component
@SessionScope
public class MainPresenter extends Presenter<MainView> {
    private Heldendokument heldendokument;
    private Binder<Heldendokument> binder;

    public MainPresenter() {
        createExample();
    }

    @Override
    public void bind() {
        binder = new Binder<>();
        binder.setBean(heldendokument);
        bindMainView();
        bindGrundeigenschaftenTab();
        view.setEmpty(heldendokument == null);
    }

    private void bindMainView() {
        binder.bind(view.nameField.getField(), h -> h.getGrundeigenschaften().getName(), (h, v) -> h.getGrundeigenschaften().withName(v));
        binder.bind(view.rasseField.getField(), h -> h.getGrundeigenschaften().getRasse(), (h, v) -> h.getGrundeigenschaften().withRasse(v));
        binder.bind(view.kulturField.getField(), h -> h.getGrundeigenschaften().getKultur(), (h, v) -> h.getGrundeigenschaften().withKultur(v));
        binder.bind(view.professionField.getField(), h -> h.getGrundeigenschaften().getProfession(), (h, v) -> h.getGrundeigenschaften().withProfession(v));
    }

    private void bindGrundeigenschaftenTab() {
        binder.bind(view.grundeigenschaftenTab.getGeschlechtField().getField(), h -> h.getGrundeigenschaften().getGeschlecht(), (h, v) -> h.getGrundeigenschaften().withGeschlecht(v));
        binder.bind(view.grundeigenschaftenTab.getGeborenField().getField(), g -> g.getGrundeigenschaften().getGeburtsdatum().format("%W, der %d. %M %j %T", Zeitrechnung.BF), (g, t) -> {
        });
        binder.forField(view.grundeigenschaftenTab.getGroesseField().getField()).withValidator(validateFloat).bind(g -> mapToString(g.getGrundeigenschaften().getGroesse()), (g, v) -> g.getGrundeigenschaften().withGroesse(mapToFloat(v)));
        binder.forField(view.grundeigenschaftenTab.getGewichtField().getField()).withValidator(validateFloat).bind(g -> mapToString(g.getGrundeigenschaften().getGewicht()), (g, v) -> g.getGrundeigenschaften().withGewicht(mapToFloat(v)));
        binder.bind(view.grundeigenschaftenTab.getHaarfarbelField().getField(), h -> h.getGrundeigenschaften().getHaarfarbe(), (h, v) -> h.getGrundeigenschaften().withHaarfarbe(v));
        binder.bind(view.grundeigenschaftenTab.getAugenfarbeField().getField(), h -> h.getGrundeigenschaften().getAugenfarbe(), (h, v) -> h.getGrundeigenschaften().withAugenfarbe(v));
        binder.bind(view.grundeigenschaftenTab.getAussehenField().getField(), h -> h.getGrundeigenschaften().getAussehen(), (h, v) -> h.getGrundeigenschaften().withAussehen(v));
        binder.bind(view.grundeigenschaftenTab.getStandField().getField(), h -> h.getGrundeigenschaften().getStand(), (h, v) -> h.getGrundeigenschaften().withStand(v));
        binder.bind(view.grundeigenschaftenTab.getTitelField().getField(), h -> h.getGrundeigenschaften().getTitel(), (h, v) -> h.getGrundeigenschaften().withTitel(v));
        binder.forField(view.grundeigenschaftenTab.getSozialstatusField().getField()).withValidator(validateInteger).bind(g -> mapToString(g.getGrundeigenschaften().getSozialstatus()), (g, v) -> g.getGrundeigenschaften().withSozialstatus(mapToInt(v)));
        binder.bind(view.grundeigenschaftenTab.getHintergrundField().getField(), h -> h.getGrundeigenschaften().getHintergrund(), (h, v) -> h.getGrundeigenschaften().withHintergrund(v));

        Arrays.stream(Eigenschaft.values()).forEach(eigenschaft -> {
            var panel = view.grundeigenschaftenTab.getEigenschaftPanel(eigenschaft);
            binder.bind(panel.getModifikatorField(),
                    h -> mapToString(h.getMod(eigenschaft)),
                    (h, v) -> {
                    });
            binder.forField(panel.getStartField()).withValidator(validateInteger).bind(
                    h -> mapToString(h.getEigenschaften().getWert(eigenschaft).getStart()),
                    (h, v) -> h.getEigenschaften().getWert(eigenschaft).withStart(mapToInt(v)));
            panel.getStartField().addValueChangeListener(event -> binder.readBean(heldendokument));
            binder.bind(panel.getAktuellField(),
                    h -> mapToString(h.getEigenschaften().getWert(eigenschaft).getStart() + h.getMod(eigenschaft)),
                    (h, v) -> {
                    });
        });

        Arrays.stream(Basiswert.values()).forEach(basiswert -> {
            var panel = view.grundeigenschaftenTab.getBasiswertPanel(basiswert);
            binder.forField(panel.getModifikatorField()).withValidator(validateInteger).bind(
                    h -> mapToString(h.getMod(basiswert)),
                    (h, v) -> {
                    });
            binder.bind(panel.getStartField(),
                    h -> mapToString(h.getBasiswerte().getWert(basiswert).start(h.getEigenschaften(), h.getSonderfertigkeiten(), h.getGrundeigenschaften())),
                    (h, v) -> {
                    });
            binder.bind(panel.getAktuellField(),
                    h -> mapToString(
                            h.getBasiswerte().getWert(basiswert).start(h.getEigenschaften(), h.getSonderfertigkeiten(), h.getGrundeigenschaften())
                            + h.getMod(basiswert)
                            + h.getBasiswerte().getWert(basiswert).getZugekauft()),
                    (h, v) -> {
                    });
            binder.forField(panel.getZugekauftField()).withValidator(validateInteger).bind(
                    h -> mapToString(h.getBasiswerte().getWert(basiswert).getZugekauft()),
                    (h, v) -> h.getBasiswerte().getWert(basiswert).withZugekauft(mapToInt(v)));
            panel.getZugekauftField().addValueChangeListener(event -> binder.readBean(heldendokument));
            binder.bind(panel.getMaxZugekauftField(),
                    h -> mapToString(basiswert.getBerechnungMaxZugekauft().apply(h.getEigenschaften())),
                    (h, v) -> {
                    });
        });
    }

    private void createExample() {
        heldendokument = new Heldendokument();
        heldendokument.getGrundeigenschaften()
                .withName("Groll von Tunichgut")
                .withAugenfarbe(Augenfarbe.DUNKELBRAUN)
                .withAussehen("schwer einzuschätzen")
                .withGeschlecht(Geschlecht.M)
                .withHaarfarbe(Haarfarbe.MITTELBRAUN)
                .withRasse(Rasse.TULAMIDE)
                .withKultur(Kultur.TULAMIDISCHE_STADTSTAATEN)
                .withProfession(Profession.GILDENMAGIER)
                .withSozialstatus(8)
                .withGeburtsdatum(new Datum(123456))
                .withGewicht(120)
                .withGroesse(220)
                .withStand(Stand.ADEPTUS)
                .withHintergrund("unbekannt")
                .withTitel(Titel.EUER_GNADEN);
        heldendokument.getEigenschaften()
                .withStart(Eigenschaft.MU, 13)
                .withStart(Eigenschaft.KL, 8)
                .withStart(Eigenschaft.IN, 9)
                .withStart(Eigenschaft.CH, 10)
                .withStart(Eigenschaft.FF, 11)
                .withStart(Eigenschaft.GE, 14)
                .withStart(Eigenschaft.KO, 14)
                .withStart(Eigenschaft.KK, 13)
                .withStart(Eigenschaft.GS, 7)
                .withLeitEigenschaft(Eigenschaft.KL);
    }
}
