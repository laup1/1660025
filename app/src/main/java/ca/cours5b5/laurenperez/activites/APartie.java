package ca.cours5b5.laurenperez.activites;


import android.os.Bundle;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.controleurs.ControleurModeles;
import ca.cours5b5.laurenperez.donnees.SauvegardeTemporaire;
import ca.cours5b5.laurenperez.modeles.MParametres;
import ca.cours5b5.laurenperez.modeles.MPartie;

public class APartie extends Activite {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_partie);

    }

    @Override
    protected void onPause() {
        super.onPause();

        ControleurModeles.sauvegarderModele(MPartie.class.getSimpleName());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ControleurModeles.sauvegarderModeleDansCetteSource(MPartie.class.getSimpleName(),
                new SauvegardeTemporaire(outState));

    }
}