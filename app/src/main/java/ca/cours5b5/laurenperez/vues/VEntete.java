package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;

import ca.cours5b5.laurenperez.R;

public class VEntete extends AppCompatButton {
    public VEntete(Context context) {
        super(context);
    }

    public VEntete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VEntete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int colonne;



     public VEntete(Context context, int colonne){
         super(context);
         this.colonne = colonne;

         setText(colonne + "\n↓\n↓");








    }
}
