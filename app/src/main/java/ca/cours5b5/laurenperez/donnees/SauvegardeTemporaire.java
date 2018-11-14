package ca.cours5b5.laurenperez.donnees;

import android.os.Bundle;

import java.util.Map;


import ca.cours5b5.laurenperez.serialisation.Jsonification;

public class SauvegardeTemporaire extends SourceDeDonnees {

    private Bundle bundle;

    public SauvegardeTemporaire(Bundle bundle){
        this.bundle = bundle;
    }


    @Override
    public void detruireSauvegarde(String cheminSauvegarde) {

        if(bundle != null){

            bundle.remove(getCle(cheminSauvegarde));

        }


    }

    @Override
    public void chargerModele(String cheminSauvegarde, ListenerChargement listenerChargement) {

        String cle = getCle(cheminSauvegarde);

        if(bundle != null && bundle.containsKey(cle)){

            String json = bundle.getString(cle);

            Map<String, Object> objetJson = Jsonification.aPartirChaineJson(json);

            listenerChargement.reagirSucces(objetJson);

        }else{
            listenerChargement.reagirErreur(new Exception());


        }
    }

    private String getCle(String cheminSauvegarde) {

        return getNomModel(cheminSauvegarde);
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {
        if(bundle != null){

            String cle = getCle(cheminSauvegarde);

            String json = Jsonification.enChaineJson(objetJson);
            bundle.putString(cle, json);

        }
    }



}