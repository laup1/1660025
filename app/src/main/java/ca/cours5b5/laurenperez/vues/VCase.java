package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

import ca.cours5b5.laurenperez.global.GCouleur;

public class VCase extends AppCompatButton {

    public VCase(Context context) {
        super(context);
    }

    public VCase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VCase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VCase(Context context, int rangee, int colonne) {
        super(context);


        this.setText(rangee + "," + colonne);


    }

    public void initialiser(){



    }

    public void afficherJeton(GCouleur jeton){

        if(jeton.equals(GCouleur.JAUNE)){

            this.setBackgroundColor(Color.YELLOW);

        } else{
            this.setBackgroundColor(Color.RED);
        }



    }
}
