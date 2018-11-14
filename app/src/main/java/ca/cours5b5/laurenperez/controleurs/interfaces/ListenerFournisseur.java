package ca.cours5b5.laurenperez.controleurs.interfaces;


import ca.cours5b5.laurenperez.modeles.Modele;

public abstract class ListenerFournisseur {

    public abstract void executer(Object... args);

    public static interface ListenerGetModele {

        void reagirAuModel(Modele modele);
    }
}
