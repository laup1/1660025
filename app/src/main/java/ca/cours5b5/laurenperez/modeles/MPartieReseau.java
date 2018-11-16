package ca.cours5b5.laurenperez.modeles;

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
    private String __idJueurInvite = GConstantes.CLE_ID_JOUEUR_INVITE;

    @AttributSerialisable
    public String idJoueurHote ;
    private String __idJueurHote = GConstantes.CLE_ID_JOUEUR_HOTE;

    public MPartieReseau(MParametresPartie parametres) {

        super(parametres);

    }


    @Override
    public String getId() {

        return idJoueurHote;
    }

    private void fournirActionRecevoirCoup(){

        ControleurAction.fournirAction(this,
                GCommande.RECEVOIR_COUP_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        //verifier

                        try{

                            int colonne = (Integer) args[0];

                            recevoirCoupReseau(colonne);


                        }catch(ClassCastException e){

                            throw new ErreurAction(e);

                        }


                    }
                });

    }



    @Override
    protected void fournirActionPlacerJeton(){

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

        //verifier

        idJoueurInvite = (String) objetJson.get(__idJueurInvite);
        idJoueurHote = (String) objetJson.get(__idJueurHote);




        super.aPartirObjetJson(enObjetJson());

    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {

        Map<String, Object> objetJson = super.enObjetJson();

        objetJson.put(__idJueurInvite, idJoueurInvite);
        objetJson.put(__idJueurHote, idJoueurHote);

        return objetJson;




    }

}
