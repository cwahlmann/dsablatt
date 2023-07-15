package de.dreierschach.dsablatt.heldendokument;

import de.dreierschach.dsalib.model.Heldendokument;
import de.dreierschach.dsalib.model.types.*;

import static de.dreierschach.dsalib.model.types.Eigenschaft.*;

public class SetupUi {
    public void init(){
        var heldendokument =new Heldendokument();
        heldendokument.getGrundeigenschaften()
                .withName("Groll von Tunichgut")
                .withAugenfarbe(Augenfarbe.DUNKELBRAUN)
                .withAussehen("schwer einzuschätzen")
                .withGeschlecht(Geschlecht.M)
                .withHaarfarbe(Haarfarbe.DUNKELBRAUN)
                .withRasse(Rasse.TULAMIDE)
                .withKultur(Kultur.TULAMIDISCHE_STADTSTAATEN)
                .withProfession(Profession.GILDENMAGIER)
                .withSozialstatus(8)
                .withGeburtsdatum(new Datum(123456789))
                .withGewicht(120)
                .withGroesse(220)
                .withHintergrund("unbekannt")
                .withTitel(Titel.EUER_GNADEN);
        heldendokument.getEigenschaften()
                .withStart(MU, 13)
                .withStart(KL, 8)
                .withStart(IN, 9)
                .withStart(CH, 10)
                .withStart(FF, 11)
                .withStart(GE, 14)
                .withStart(KO, 14)
                .withStart(KK, 13);
    }
}
