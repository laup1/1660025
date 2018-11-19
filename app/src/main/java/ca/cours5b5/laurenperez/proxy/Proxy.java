package ca.cours5b5.laurenperez.proxy;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class Proxy {
    private String cheminServeur;
    protected DatabaseReference noeudServeur;

    public Proxy(String cheminServeur){
        this.cheminServeur = cheminServeur;
        this.connecterAuServeur();
    }

    public void connecterAuServeur(){
        /*
         * Obtenir le noeud
         */
        noeudServeur = FirebaseDatabase.getInstance().getReference(cheminServeur);
    }

    public void deconnecterDuServeur(){
        /*
         * Oublier le noeud
         */
        noeudServeur = null;
    }

    public abstract void detruireValeurs();
}