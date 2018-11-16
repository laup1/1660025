package ca.cours5b5.laurenperez.activites;

import android.app.Activity;
import android.os.Bundle;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.controleurs.ControleurModeles;
import ca.cours5b5.laurenperez.controleurs.ControleurPartieReseau;
import ca.cours5b5.laurenperez.controleurs.interfaces.Fournisseur;
import ca.cours5b5.laurenperez.donnees.SauvegardeTemporaire;
import ca.cours5b5.laurenperez.modeles.MPartie;
import ca.cours5b5.laurenperez.modeles.MPartieReseau;

public class APartieReseau extends Activity implements Fournisseur {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_partie_reseau);

    }

    @Override
    protected void onPause() {
        super.onPause();

        ControleurPartieReseau.getInstance().detruireSauvegardeServeur();

        ControleurPartieReseau.getInstance().deconnecterDuServeur();



    }

    @Override
  protected void onDestroy(){
        super.onDestroy();
    }


    @Override
    protected void onResume(){

        super.onResume();

        //bonus

        ControleurModeles.detruireModele(MPartieReseau.class.getSimpleName());
    }
}
