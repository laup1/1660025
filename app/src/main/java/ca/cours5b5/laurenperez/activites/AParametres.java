package ca.cours5b5.laurenperez.activites;


import android.os.Bundle;

import java.util.ArrayList;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.controleurs.ControleurAction;
import ca.cours5b5.laurenperez.controleurs.ControleurModeles;
import ca.cours5b5.laurenperez.controleurs.interfaces.Fournisseur;
import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.laurenperez.global.GCommande;
import ca.cours5b5.laurenperez.modeles.MParametres;
import ca.cours5b5.laurenperez.modeles.MPartie;
import ca.cours5b5.laurenperez.vues.VGrille;


public class AParametres extends Activite implements Fournisseur{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        fournirActions();

    }


    private void fournirActions() {

        ControleurAction.fournirAction(this,
                GCommande.EFFACER_PARTIE_COURANTE,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        ControleurModeles.detruireModele(MPartie.class.getSimpleName());
                        VGrille.entetesADesactiver =  new ArrayList<>();

                    }
                });
    }

    @Override
    protected void onPause() {
        super.onPause();

        ControleurModeles.sauvegarderModele(MParametres.class.getSimpleName());

    }


}
