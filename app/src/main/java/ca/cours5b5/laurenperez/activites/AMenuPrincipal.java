package ca.cours5b5.laurenperez.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.laurenperez.R;

public class AMenuPrincipal extends Activite {

    static{
        Log.d("atelier04", AParametres.class.getSimpleName()+ ":static");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);
        creerLog("onCreate");

        Button boutonParametres = this.findViewById(R.id.buttonMenuPrincipal);
        boutonParametres.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent vueParametres = new Intent(AMenuPrincipal.this, AParametres.class);
                AMenuPrincipal.this.startActivity(vueParametres);
            }
        });

        Button boutonJouer = this.findViewById(R.id.button);
        boutonJouer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent vueMPartie = new Intent(AMenuPrincipal.this, APartie.class);
                AMenuPrincipal.this.startActivity(vueMPartie);
            }
        });

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
