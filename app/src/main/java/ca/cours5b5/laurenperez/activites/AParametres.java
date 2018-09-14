package ca.cours5b5.laurenperez.activites;


import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;
import java.util.logging.Logger;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.Serialisation.Jsonification;
import ca.cours5b5.laurenperez.modeles.MParametres;
import ca.cours5b5.laurenperez.vues.VParametres;

public class AParametres extends Activite {


    private  VParametres parametres ;



    static{
        Log.d("atelier04", AParametres.class.getSimpleName()+ ":static");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        parametres = new VParametres(this);

        if(savedInstanceState != null) {
            restaurerParametres(savedInstanceState);
        }

        creerLog("onCreate");
        Log.d("log", this.getResources().getString(R.string.hello_world));
        Log.d("log", this.getResources().getString(R.string.orientation));


    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        sauvegarderParametres(outState);






        creerLog("onSaveInstanceState");
    }


    private void restaurerParametres( Bundle savedInstanceState){

        String json = savedInstanceState.getString("MParametres");
        Map<String,Object> objetJson =  Jsonification.enObjetJson(json);
        MParametres.instance.aPartirObjetJson(objetJson);
        Log.d("Atelier05", AParametres.class.getSimpleName()+ "::restaurerParametres, clé: MParametres");
        Log.d("Atelier05", AParametres.class.getSimpleName()+ "::restaurerParametres, json:" + objetJson.toString());


    }

    private void sauvegarderParametres( Bundle outState){

        Map<String, Object> objetJson = MParametres.instance.enObjetJson();

        String json = Jsonification.enChaine(objetJson);

        outState.putString("MParametres", json);
        Log.d("Atelier05", AParametres.class.getSimpleName()+ "::SauvagarderParametres, clé: MParametres");
        Log.d("Atelier05", AParametres.class.getSimpleName()+ "::SauvagarderParametres, json:" + objetJson.toString());

    }


    @Override
    protected void onResume(){
        super.onResume();
        creerLog("onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        creerLog("onPause");

        }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        creerLog("onDestroy");
    }


}
