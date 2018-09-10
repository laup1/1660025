package ca.cours5b5.laurenperez.activites;


import android.os.Bundle;
import android.util.Log;
import java.util.Map;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.Serialisation.Jsonification;
import ca.cours5b5.laurenperez.modeles.MParametres;
import ca.cours5b5.laurenperez.vues.VParametres;

public class AParametres extends Activite {


    private  VParametres parametres = new VParametres(this);
    private  MParametres modele = new MParametres();


    static{
        Log.d("atelier04", AParametres.class.getSimpleName()+ ":static");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        if(savedInstanceState != null) {

            Map<String, Object> objetJson = modele.enObjetJson();

            for (Map.Entry<String, Object> entry : objetJson.entrySet()) {

                String cle = entry.getKey();
                Object valeur = entry.getValue();
                String json = savedInstanceState.getString(cle);


            }
        }



        creerLog("onCreate");
        Log.d("log", this.getResources().getString(R.string.hello_world));
        Log.d("log", this.getResources().getString(R.string.orientation));


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
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        Map<String, Object> objetJson = modele.enObjetJson();

        String json = Jsonification.enChaine(objetJson);

        outState.putInt("__hauteur", Integer.parseInt(json));
        outState.putInt("__largeur", Integer.parseInt(json));
        outState.putInt("__pourGagner", Integer.parseInt(json));



        creerLog("onSaveInstanceState");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        creerLog("onDestroy");
    }


    private void restaurerParametres( Bundle savedInstanceState){

    }

    private void sauvegarderParametres( Bundle outState){

    }




}
