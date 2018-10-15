package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;
import android.widget.GridLayout;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.controleurs.ControleurObservation;
import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.laurenperez.modeles.MParametres;

import ca.cours5b5.laurenperez.modeles.MParametresPartie;
import ca.cours5b5.laurenperez.modeles.MPartie;
import ca.cours5b5.laurenperez.modeles.Modele;

public class VPartie extends Vue {

    public VPartie(Context context) {
        super(context);
    }

    public VPartie(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private VGrille grille;
    @Override
    protected  void onFinishInflate(){
        super.onFinishInflate();
        Log.d("atelier06", "VPartie.onFinishInflate");

        grille = findViewById(R.id.vgrille);
        //Button bu = new Button(this.getContext());
         //bu.setText("iii");

       // grille.addView(bu );
       // VCase gr = new VCase(this.getContext(), 0,1);
        //grille.addView(gr);
        initialiser();




    }

    private void initialiser(){

        observerPartie();

    }

    private void observerPartie(){

        Log.d("atelier06", "VPartie.observerPartie");

        ControleurObservation.observerModele(MPartie.class.getSimpleName(), new ListenerObservateur() {

            @Override
            public void reagirChangementAuModele(Modele modele) {


                initialiserGrille(getPartie(modele));
                Log.d("atelier06", "VPartie.reagir");



            }
        });

    }

    private MPartie getPartie(Modele modele){

        MPartie partie = (MPartie) modele;
        return partie;

    }

    private void initialiserGrille(MPartie partie){

        grille.initialiser(partie.getParametres().getHauteur(), partie.getParametres().getLargeur());

    }












}
