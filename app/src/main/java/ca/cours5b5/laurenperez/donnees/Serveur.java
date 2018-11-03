package ca.cours5b5.laurenperez.donnees;

import java.util.Map;

public final  class Serveur  extends SourceDeDonnees{


    private Serveur(){}

   // private static final Serveur instance ;

  //  public static Serveur getInstance(){
     //   return instance;
    //}

    @Override
    public void effacer(String cheminSauvegarde) {

    }

    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde) {
        return null;
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {

    }

   // @Override
   // public void detruireSauvegarde(String cheminSauvegarde) {
//bonus
   // }
}
