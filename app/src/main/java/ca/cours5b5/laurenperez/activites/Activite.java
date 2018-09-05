package ca.cours5b5.laurenperez.activites;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class Activite extends AppCompatActivity {

    public void creerLog(String nomMethode){
        Log.d("atelier04", this.getClass().getSimpleName() + ":"+ nomMethode);
    }
}
