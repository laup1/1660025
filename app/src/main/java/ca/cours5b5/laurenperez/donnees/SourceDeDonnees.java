package ca.cours5b5.laurenperez.donnees;

import java.util.Map;


public abstract class SourceDeDonnees {

    public abstract void detruireSauvegarde(final String cheminSauvegarde);


    public abstract Map<String, Object> chargerModele(final String cheminSauvegarde);

    public abstract void sauvegarderModele(final String cheminSauvegarde, final Map<String, Object> objetJson);

    protected String getNomModel(String cheminSauvergarde){

        String nomModele = cheminSauvergarde.substring(0, (cheminSauvergarde.indexOf('/')-1));
        return nomModele;
    }
}
