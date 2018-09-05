package ca.cours5b5.laurenperez.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

import ca.cours5b5.laurenperez.R;

public class AParametres extends Activite {

    private Spinner spinnerHauteur, spinnerLargeur, spinnerPourGagner ;

    static{
        Log.d("atelier04", AParametres.class.getSimpleName()+ ":static");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        creerLog("onCreate");


        spinnerHauteur = (Spinner) findViewById(R.id.spinnerHauteur);

        ArrayAdapter<Integer> adapterHauteur = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item);
        spinnerHauteur.setAdapter(adapterHauteur);
        adapterHauteur.addAll(4, 5, 6, 7, 8, 9, 10);
        spinnerHauteur.setSelection(2);


        spinnerLargeur = (Spinner) findViewById(R.id.spinnerLargeur);

        ArrayAdapter<Integer> adapterLargeur = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item);
        spinnerLargeur.setAdapter(adapterLargeur);
        adapterLargeur.addAll(4, 5, 6, 7, 8, 9, 10);
        spinnerLargeur.setSelection(3);


        spinnerPourGagner = (Spinner) findViewById(R.id.spinnerPourGagner);

        ArrayAdapter<Integer> adapterPourGagner = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item);
        spinnerPourGagner.setAdapter(adapterPourGagner);
        adapterPourGagner.addAll(3, 4);
        spinnerPourGagner.setSelection(1);



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
        creerLog("onSaveInstanceState");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        creerLog("onDestroy");
    }




}
