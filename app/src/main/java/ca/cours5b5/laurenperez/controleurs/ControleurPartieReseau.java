package ca.cours5b5.laurenperez.controleurs;

import ca.cours5b5.laurenperez.proxy.ProxyListe;

public class ControleurPartieReseau {


    private static final ControleurPartieReseau instance = new ControleurPartieReseau();
    public static ControleurPartieReseau getInstance(){



    }


    private ProxyListe proxyEmettreCoups;
    private ProxyListe proxyRecevoirCoups;


    public void connecterAuServeur(){

    }

    private void connecterAuServeur(String idJueurHote){

    }

    private void connecterEnTantQueJoueurHote(String cheminCoupsJouerHote, String cheminCoupsJouerInvite){

    }

    private void connecterEnTantQueJoueurInvite(String cheminCoupsJouerHote, String cheminCoupsJouerInvite){

    }

    public void deconnecterDuServeur(){

    }

    public void transmettreCoup(Integer idColonne){

    }

    private String getCheminCoupsJoueurInvite(String idJueurHote){

    }

    private String getCheminPartie(String idJoueurHote){

    }
    public void detruireSauvegardeServeur(){

    }


}
