package ca.cours5b5.laurenperez.controleurs;

import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.laurenperez.donnees.Serveur;
import ca.cours5b5.laurenperez.global.GCommande;
import ca.cours5b5.laurenperez.global.GConstantes;
import ca.cours5b5.laurenperez.modeles.Identifiable;
import ca.cours5b5.laurenperez.modeles.MPartieReseau;
import ca.cours5b5.laurenperez.modeles.Modele;
import ca.cours5b5.laurenperez.proxy.ProxyListe;
import ca.cours5b5.laurenperez.usagers.UsagerCourant;

public class ControleurPartieReseau {

    private static final ControleurPartieReseau instance = new ControleurPartieReseau();
    public static ControleurPartieReseau getInstance() {return instance;}

    private ProxyListe proxyEmettreCoups;
    private ProxyListe proxyRecevoirCoups;

    public void connecterAuServeur(){

        ControleurModeles.getModele(MPartieReseau.class.getSimpleName(), new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                String idJoueurHote = ((Identifiable)modele).getId();
                connecterAuServeur(idJoueurHote);
            }
        });
    }

    private void connecterAuServeur(String idJoueurHote) {

        String idUtilisateur = UsagerCourant.getId();

        String cheminHote = getCheminCoupsJoueurHote(idJoueurHote);
        String cheminInv = getCheminCoupsJoueurInvite(idJoueurHote);

        if(idUtilisateur.equals(idJoueurHote)){
            connecterEnTantQueJoueurHote(cheminHote, cheminInv);
        }else{
            connecterEnTantQueJoueurInvite(cheminHote, cheminInv);
        }

        proxyEmettreCoups.connecterAuServeur();
        proxyRecevoirCoups.connecterAuServeur();
        proxyRecevoirCoups.setActionNouvelItem(GCommande.RECEVOIR_COUP_RESEAU);
    }

    private void connecterEnTantQueJoueurHote(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {

        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurHote);
        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurInvite);
    }

    private void connecterEnTantQueJoueurInvite(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {

        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurInvite);
        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurHote);
    }

    public void deconnecterDuServeur(){

        proxyEmettreCoups.detruireValeurs();

        proxyEmettreCoups.deconnecterDuServeur();
        proxyRecevoirCoups.deconnecterDuServeur();
    }

    public void transmettreCoup(Integer idColonne){

        proxyEmettreCoups.ajouterValeur(idColonne);
    }

    private String getCheminPartie(String idJoueurHote){

        String cheminPartie = MPartieReseau.class.getSimpleName() + "/" + idJoueurHote;
        return cheminPartie;
    }

    private String getCheminCoupsJoueurHote(String idJoueurHote){

        String cheminHote = getCheminPartie(idJoueurHote);
        cheminHote += "/" + GConstantes.CLE_COUPS_JOUEUR_HOTE;
        return cheminHote;
    }

    private String getCheminCoupsJoueurInvite(String idJoueurHote){

        String cheminInv = getCheminPartie(idJoueurHote);
        cheminInv += "/" + GConstantes.CLE_COUPS_JOUEUR_INVITE;
        return cheminInv;
    }

    public void detruireSauvegardeServeur() {

        String cheminPartie = null;


        Serveur.getInstance().detruireSauvegarde(cheminPartie);
        ControleurModeles.getModele(MPartieReseau.class.getSimpleName(), new ListenerGetModele() {
            @Override
            public void reagirAuModele(Modele modele) {
                String idJoueurHote = ((Identifiable)modele).getId();

                String cheminPartie = getCheminPartie(idJoueurHote);
                Serveur.getInstance().detruireSauvegarde(cheminPartie);
            }
        });
    }
}