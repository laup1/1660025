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


    }

    public void initialiser(){

       // findViewById(R.id.vgrille);
       // creerGrille(MParametres.instance.getParametresPartie().getHauteur(), MParametres.instance.getParametresPartie().getLargeur());
        entetes = new ArrayList<>();


    }

    void creerGrille(int hauteur, int largeur){



         nombreRanges = hauteur;
         initialiser();
         initialiserColonnes(largeur);
         ajouterEnTetes(largeur);
        ajouterCases(hauteur, largeur);



    }

    private void initialiserColonnes(int largeur){

        colonnesDeCases = new ArrayList<>(largeur);

    }

    private void ajouterEnTetes(int largeur){

        for(int i = 0; i < largeur; i++){

            VEntete entete = new VEntete( super.getContext(), i);
            entetes.add(entete);
            this.addView(entete , getMiseEnPageEntete(i));
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



        Spec specRangee = GridLayout.spec(rangee, 1.0f);
        Spec specColonne = GridLayout.spec(colonne, 3.0f);

        mesParams.columnSpec = specColonne;
        mesParams.rowSpec = specRangee;


        return  mesParams;

    }

    private void ajouterCases(int hauteur, int largeur){

        for (int i = 0; i < largeur ; i++){

            int j1 = 0;
            int i1 = 0;

            for (int j=0; j< hauteur ; j++){

                j1 = hauteur - j - 1;
                i1 = i;

                VCase cases = new VCase(super.getContext(),j1, i1);

                colonnesDeCases.add(cases);

                addView(cases, getMiseEnPageCase(j, i ));

                Log.d("atelier06", "cases" + i + j);

                j1--;
                i1++;
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
        


        Spec specRangee = GridLayout.spec(rangee +1, 1.0f);
        Spec specColonne = GridLayout.spec(colonne, 1.0f);
        mesParams.columnSpec = specColonne;
        mesParams.rowSpec = specRangee;

        return  mesParams;

    }
}
