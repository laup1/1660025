package ca.cours5b5.laurenperez.modeles;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.laurenperez.controleurs.ControleurAction;
import ca.cours5b5.laurenperez.controleurs.ControleurPartieReseau;
import ca.cours5b5.laurenperez.controleurs.interfaces.Fournisseur;
import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.laurenperez.exceptions.ErreurAction;
import ca.cours5b5.laurenperez.exceptions.ErreurSerialisation;
import ca.cours5b5.laurenperez.global.GCommande;
import ca.cours5b5.laurenperez.global.GConstantes;
import ca.cours5b5.laurenperez.serialisation.AttributSerialisable;
import ca.cours5b5.laurenperez.usagers.UsagerCourant;

public class MPartieReseau extends MPartie implements Fournisseur, Identifiable {


    @AttributSerialisable
    public String idJoueurInvite;
    private String __idJoueurInvite = GConstantes.CLE_ID_JOUEUR_INVITE;

    @AttributSerialisable
    public String idJoueurHote;
    private String __idJoueurHote = GConstantes.CLE_ID_JOUEUR_HOTE;

    public MPartieReseau(MParametresPartie parametres) {
        super(parametres);

        fournirActionPlacerJeton();
        fournirActionRecevoirCoup();
    }

    public String getId() {
        /*
         * utiliser l'id du joueur hôte
         */
        return idJoueurHote;
    }

    private void fournirActionRecevoirCoup() {
        ControleurAction.fournirAction(this,
                GCommande.RECEVOIR_COUP_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        //verifier
                        try{
                            int colonne = ((Long) args[0]).intValue();
                            recevoirCoupReseau(colonne);
                        }catch(ClassCastException e){
                            throw new ErreurAction(e);
                        }
                    }
                });
    }

    @Override
    protected void fournirActionPlacerJeton() {
        /*
         * En plus de jouer le coup, le transmettre via
         *  le ControleurPartieReseau
         *
         *  ATTENTION au @Override. Le code qui fournit l'action
         *  PLACER_JETON dans la classe parent MPartie
         *  ne doit **pas** s'exécuter
         */

        ControleurAction.fournirAction(this,
                GCommande.JOUER_COUP_ICI,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        try{
                            int colonne = (Integer) args[0];
                            jouerCoup(colonne);
                            ControleurPartieReseau.getInstance().transmettreCoup(colonne);
                        }catch(ClassCastException e){
                            throw new ErreurAction(e);
                        }
                    }
                });
    }

    private void recevoirCoupReseau(int colonne){
        jouerCoup(colonne);
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        /*
         * charger les champs
         * appeler aussi super
         */

        idJoueurHote = (String) objetJson.get(__idJoueurHote);
        idJoueurInvite = (String) objetJson.get(__idJoueurInvite);

        super.aPartirObjetJson(objetJson);
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        /*
         * sauvegarder les champs
         * appeler aussi super
         */
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__idJoueurInvite, idJoueurInvite);
        objetJson.put(__idJoueurHote, idJoueurHote);

        objetJson = super.enObjetJson();
        return objetJson;
    }
}