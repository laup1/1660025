package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.controleurs.ControleurObservation;
import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.laurenperez.modeles.MParametres;

import ca.cours5b5.laurenperez.modeles.MPartie;
import ca.cours5b5.laurenperez.modeles.Modele;

public class VPartie extends Vue {

    private VGrille grille;
    GridLayout grilleLayout;

    public VPartie(Context context) {
        super(context);
    }

    public VPartie(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected  void onFinishInflate(){
        super.onFinishInflate();
        initialiser();
        GridLayout grilleLayout = (GridLayout) super.findViewById(R.id.gridLayout3);
        grilleLayout.addView(grille);


    }

    private void initialiser(){

        observerPartie();

    }

    private void observerPartie(){

        ControleurObservation.observerModele(MParametres.class.getSimpleName(), new ListenerObservateur() {
            @Override
            public void reagirChangementAuModele(Modele modele) {

                MPartie partie = getPartie(modele);
                initialiserGrille(partie);

            }
        });

    }

    private MPartie getPartie(Modele modele){

        MPartie partie = (MPartie)modele;
        return partie;

    }

    private void initialiserGrille(MPartie partie){

        grille =  new VGrille(this.getContext());
        grille.initialiser(partie.getParametres().getHauteur(), partie.getParametres().getLargeur());






    }












}
