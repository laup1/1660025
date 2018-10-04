package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
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

    public GridLayout getGrille() {
        return grille;
    }

    public GridLayout grille;


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
        initialiser(MParametres.instance.getParametresPartie().getHauteur(), MParametres.instance.getParametresPartie().getLargeur());


    }

    public void initialiser(int hauteur, int largeur){

        creerGrille(hauteur, largeur);


    }

    void creerGrille(int hauteur, int largeur){

         grille = super.findViewById(R.id.gridLayout3);
         grille.setRowCount(hauteur);
         grille.setColumnCount(largeur);

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

        for(int i = 0; i <largeur; i++){

            VEntete entete = new VEntete(super.getContext(), i);
            entetes.add(entete);
            grille.addView(entete , getMiseEnPageEntete(largeur));

        }

    }

    private LayoutParams getMiseEnPageEntete(int colonne){

        LayoutParams mesParams = (LayoutParams) grille.getLayoutParams();
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

        for (int i =0; i< hauteur; i++){
            for (int j=0; j<largeur; j++){
                VCase cases = new VCase(super.getContext(),hauteur,largeur);
                colonnesDeCases.add(cases);
                grille.addView(cases, getMiseEnPageCase(hauteur,largeur));
            }
        }

    }

    private LayoutParams getMiseEnPageCase(int rangee, int colonne){

        LayoutParams mesParams = (LayoutParams) grille.getLayoutParams();
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
