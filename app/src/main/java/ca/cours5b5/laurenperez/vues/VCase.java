package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.modeles.MJeton;


public class VCase extends AppCompatButton {
    public boolean isCouleur() {
        return couleur;
    }

    private boolean couleur = false;

    private int rangee;
    private boolean anime;
    private int colonne;




    public VCase(Context context) {
        super(context);
        initialiser();
    }

    public VCase(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialiser();
    }

    public VCase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialiser();
    }


    public VCase(Context context, int rangee, int colonne) {
        super(context);

        this.colonne = colonne;
        this.rangee = rangee;
        // Atelier08: afficher les indices
        setText(""+rangee+","+colonne);

        initialiser();
    }

    private void initialiser() {
        
        setEnabled(false);

        setBackgroundColor(getResources().getColor(R.color.VIDE, null));

    }

    public void afficherJeton(MJeton jeton) {

        afficherCouleurJeton(jeton);
        if(!anime){
            animer();
        }

    }


    public void animer() {

        float yAnimation = -getResources().getDisplayMetrics().heightPixels + ( getHeight() * rangee);

        this.setY(yAnimation);

        //float  = 0;


        this.animate().translationY(0f).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(450);

        anime = true;

    }


    private void afficherCouleurJeton(MJeton jeton) {
        switch (jeton.getCouleur()){

            case ROUGE:

                setBackgroundColor(getResources().getColor(R.color.ROUGE, null));
                couleur = true;
                break;

            case JAUNE:

                setBackgroundColor(getResources().getColor(R.color.JAUNE, null));
                couleur = true;
                break;

        }
    }


}
