package ca.cours5b5.laurenperez.donnees;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public final  class Serveur  extends SourceDeDonnees{


    private Serveur(){}

    private static final Serveur instance = new Serveur();

    public static Serveur getInstance(){
        return instance;
    }


    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde) {

        return null;
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {


        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);
        noeud.setValue(objetJson);



    }
    @Override
    public void detruireSauvegarde(String cheminSauvegarde) {

        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);
        noeud.removeValue();

    }
}