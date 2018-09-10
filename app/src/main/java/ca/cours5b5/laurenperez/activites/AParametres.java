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
import ca.cours5b5.laurenperez.vues.VParametres;

public class AParametres extends Activite {



    static{
        Log.d("atelier04", AParametres.class.getSimpleName()+ ":static");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        VParametres parametres = new VParametres(this);



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
        creerLog("onSaveInstanceState");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        creerLog("onDestroy");
    }




}
