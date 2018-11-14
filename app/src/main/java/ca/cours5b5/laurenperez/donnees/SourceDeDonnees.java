package ca.cours5b5.laurenperez.donnees;

import java.util.Map;


public abstract class SourceDeDonnees {

    public abstract void detruireSauvegarde(final String cheminSauvegarde);




    public abstract void sauvegarderModele(final String cheminSauvegarde, final Map<String, Object> objetJson);

    protected String getNomModel(String cheminSauvegarde){

        //String nomModele = cheminSauvergarde.substring(0, (cheminSauvegarde.indexOf('/')-1));
        String nomModele = cheminSauvegarde.split("/")[0];
        return nomModele;
    }

    public abstract void chargerModele(final String cheminSauvegarde, final ListenerChargement listenerChargement);
}
