package ca.cours5b5.laurenperez.activites;


import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.Serialisation.Jsonification;
import ca.cours5b5.laurenperez.modeles.MParametres;
import ca.cours5b5.laurenperez.modeles.MParametresPartie;
import ca.cours5b5.laurenperez.vues.VParametres;
import ca.cours5b5.laurenperez.vues.VPartie;

public class APartie extends Activite {

    private VPartie partie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        partie = new VPartie(this);
       setContentView(R.layout.activity_apartie);

        if(savedInstanceState != null) {
            restaurerParametres(savedInstanceState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        sauvegarderParametres(outState);






        creerLog("onSaveInstanceState");
    }

    private void restaurerParametres( Bundle savedInstanceState){

        String json = savedInstanceState.getString("MPartie");
        Map<String,Object> objetJson =  Jsonification.enObjetJson(json);
        MParametresPartie.aPartirMParametres(MParametres.instance );
        Log.d("Atelier05", AParametres.class.getSimpleName()+ "::restaurerParametres, clé: MPartie");
        Log.d("Atelier05", AParametres.class.getSimpleName()+ "::restaurerParametres, json:" + objetJson.toString());


    }

    private void sauvegarderParametres( Bundle outState){

        Map<String, Object> objetJson = MParametres.instance.enObjetJson();

        String json = Jsonification.enChaine(objetJson);

        outState.putString("MParametres", json);
        Log.d("Atelier05", AParametres.class.getSimpleName()+ "::SauvagarderParametres, clé: MPartie");
        Log.d("Atelier05", AParametres.class.getSimpleName()+ "::SauvagarderParametres, json:" + objetJson.toString());

    }
}
