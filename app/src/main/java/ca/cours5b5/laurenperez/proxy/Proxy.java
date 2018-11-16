package ca.cours5b5.laurenperez.proxy;

import com.google.firebase.database.DatabaseReference;

public abstract class Proxy {

    private String cheminServeur;

    protected DatabaseReference noeudServeur;

    public Proxy(String cheminServeur){

    }

    public void connecterAuServeur(){

    }

    public void deconnecterAuServeur(){

    }

    public abstract void detruireValeurs();
}
