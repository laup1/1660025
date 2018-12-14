package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.firebase.ui.auth.data.model.Resource;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    private boolean active = true;

    public VEntete(Context context, int colonne) {
        super(context);

        // Atelier 08: afficher la colonne pour deboguer
        setText(colonne + "\n" + getResources().getString(R.string.entete));

        setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ripple_effect));

        this.colonne = colonne;
        //setBackgroundDrawable(android:background="@drawable/ripple_effect");
    }

    public int getColonne() {
        return colonne;
    }
}
