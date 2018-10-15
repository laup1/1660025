package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridLayout;
import java.util.ArrayList;
import java.util.List;
import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.controleurs.ControleurObservation;
import ca.cours5b5.laurenperez.modeles.MParametres;
import ca.cours5b5.laurenperez.modeles.MParametresPartie;

public class VGrille extends GridLayout {

    public VGrille(Context context) {
        super(context);
    }

    public VGrille(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VGrille(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VGrille(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private int nombreRanges;

    public VGrille getGrille() {
        return this;
    }




    private class Colonne extends ArrayList<VCase>{

        List<VCase> colonnes;

        public Colonne(){
            colonnes = new ArrayList<VCase>();
        }

    }

    private List<VCase> colonnesDeCases;

    private List<VEntete> entetes;

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        //initialiser(MParametres.instance.getParametresPartie().getHauteur(), MParametres.instance.getParametresPartie().getLargeur());
        //getGrille();

    }

    public void initialiser(int hauteur, int largeur){

        creerGrille(hauteur, largeur);


    }

    void creerGrille(int hauteur, int largeur){

       // this.findViewById(R.id.vgrille);
         //this.setRowCount(hauteur);
        //this.setColumnCount(largeur);

         nombreRanges = hauteur;
         initialiserColonnes(largeur);
         ajouterEnTetes(largeur);
       ajouterCases(hauteur, largeur);



    }

    private void initialiserColonnes(int largeur){

        colonnesDeCases = new ArrayList<>(largeur);
        entetes = new ArrayList<VEntete>();
    }

    private void ajouterEnTetes(int largeur){

        for(int i = 0; i < largeur; i++){

            VEntete entete = new VEntete(super.getContext(), i);
            entetes.add(entete);
            addView(entete , getMiseEnPageEntete(i));
            Log.d("atelier06", String.valueOf(i));

        }

    }

    private LayoutParams getMiseEnPageEntete(int colonne){

        LayoutParams mesParams = new LayoutParams();
        mesParams.width = 0;
        mesParams.height = 0;
        mesParams.setGravity(Gravity.FILL);
        mesParams.rightMargin = 5;
        mesParams.leftMargin = 5;

         int rangee = 0;

         float poidsRangee = 0,
                poidsColonne = 3;

        Spec specRangee = GridLayout.spec(rangee, poidsRangee);
        Spec specColonne = GridLayout.spec(colonne, poidsColonne);

        mesParams.columnSpec = specColonne;
        mesParams.rowSpec = specRangee;


        return  mesParams;

    }

    private void ajouterCases(int hauteur, int largeur){

        for (int i =0; i< largeur; i++){
            for (int j=1; j<hauteur+1; j++){
                VCase cases = new VCase(super.getContext(),i,j);
                colonnesDeCases.add(cases);
                addView(cases, getMiseEnPageCase(i,j));
                Log.d("atelier06", "cases" + i + j);
            }
        }

    }

    private LayoutParams getMiseEnPageCase(int rangee, int colonne){

        LayoutParams mesParams = new LayoutParams();
        mesParams.width = 0;
        mesParams.height = 0;
        mesParams.setGravity(Gravity.FILL);
        mesParams.rightMargin = 5;
        mesParams.leftMargin = 5;
        
        float poidsRangee = 0,
                poidsColonne = 0;

        Spec specRangee = GridLayout.spec(rangee, poidsRangee);
        Spec specColonne = GridLayout.spec(colonne, poidsColonne);
        mesParams.columnSpec = specColonne;
        mesParams.rowSpec = specRangee;

        return  mesParams;

    }
}
