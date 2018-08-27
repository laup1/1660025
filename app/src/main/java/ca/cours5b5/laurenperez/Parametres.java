package ca.cours5b5.laurenperez;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Parametres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);


        Log.d("log",this.getResources().getString(R.string.hello_world));
        Log.d("log",this.getResources().getString(R.string.orientation));



    }
}
