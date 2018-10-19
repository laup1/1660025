package ca.cours5b5.laurenperez.controleurs;

import android.util.ArrayMap;
import android.util.Log;

import java.util.Map;

import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.laurenperez.modeles.MParametres;
import ca.cours5b5.laurenperez.modeles.MParametresPartie;
import ca.cours5b5.laurenperez.modeles.MPartie;
import ca.cours5b5.laurenperez.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListenerObservateur> observations = new ArrayMap<>();

    private static MPartie partie;

    static{

    }

    public static void observerModele(String nomModele, final ListenerObservateur listenerObservateur){

            partie = new MPartie(MParametres.instance.getParametresPartie().cloner());

            Modele modele;


            if(nomModele.equalsIgnoreCase(MParametres.class.getSimpleName())) {
                modele = MParametres.instance;
                //observations.put(MParametres.instance, listenerObservateur);
                //lancerObservation(MParametres.instance);

            } else {
                modele = ControleurObservation.partie;
                //observations.put(ControleurObservation.partie, listenerObservateur);
               // lancerObservation(ControleurObservation.partie);

            }

        observations.put(modele, listenerObservateur);
         lancerObservation(modele);




        Log.d("atelier06", "ControleurObservation.observerModele");
    }


    public static void lancerObservation(Modele modele){


        ListenerObservateur listenerObservateur = observations.get(modele);

        if(listenerObservateur != null )

            listenerObservateur.reagirNouveauModele(modele);

    }
}
