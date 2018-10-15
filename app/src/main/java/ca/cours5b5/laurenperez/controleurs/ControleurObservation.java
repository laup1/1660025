package ca.cours5b5.laurenperez.controleurs;

import android.util.ArrayMap;
import android.util.Log;

import java.util.Map;

import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.laurenperez.modeles.MParametres;
import ca.cours5b5.laurenperez.modeles.MPartie;
import ca.cours5b5.laurenperez.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListenerObservateur> observations = new ArrayMap<>();

    private static MPartie partie;

    static{

    }

    public static void observerModele(String nomModele, final ListenerObservateur listenerObservateur){

            partie = new MPartie(MParametres.instance.getParametresPartie().cloner());
            observations.put((nomModele.equalsIgnoreCase(MParametres.class.getSimpleName())? MParametres.instance : partie), listenerObservateur);
            lancerObservation((nomModele.equalsIgnoreCase(MParametres.class.getSimpleName())? MParametres.instance : partie));
        Log.d("atelier06", "ControleurObservation.observerModele");
    }


    public static void lancerObservation(Modele modele){

      observations.get(modele).reagirNouveauModele(modele);

    }
}
