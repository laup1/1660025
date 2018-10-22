package ca.cours5b5.laurenperez.activites;

import android.os.Bundle;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.controleurs.ControleurModeles;
import ca.cours5b5.laurenperez.controleurs.interfaces.Fournisseur;
import ca.cours5b5.laurenperez.modeles.MParametres;

public class AParametres extends Activite implements Fournisseur{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

    }

    @Override
    protected void onPause() {
        super.onPause();

        ControleurModeles.sauvegarderModele(MParametres.class.getSimpleName());

    }

}
