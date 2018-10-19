package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import java.util.ArrayList;
import java.util.List;
import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.controleurs.Action;
import ca.cours5b5.laurenperez.controleurs.ControleurAction;
import ca.cours5b5.laurenperez.controleurs.ControleurObservation;
import ca.cours5b5.laurenperez.controleurs.interfaces.Fournisseur;
import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.laurenperez.global.GCommande;
import ca.cours5b5.laurenperez.global.GCouleur;
import ca.cours5b5.laurenperez.modeles.MGrille;
import ca.cours5b5.laurenperez.modeles.MParametres;
import ca.cours5b5.laurenperez.modeles.MParametresPartie;
import ca.cours5b5.laurenperez.modeles.MPartie;

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
            colonnes = new ArrayList<>();
        }

    }

    private List<VCase> colonnesDeCases;
    private List<VEntete> entetes;

    private VCase[][] lesCases;
    private Action action;



    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        Log.d("atelier07", VGrille.class.getSimpleName() + "::Grid:Onfinishinflate");


    }

    public void initialiser(){


        entetes = new ArrayList<>();


    }



    void creerGrille(int hauteur, int largeur){
         nombreRanges = hauteur;
         initialiser();
        initialiserTableauDeCases(hauteur,largeur);
         initialiserColonnes(largeur);
         ajouterEnTetes(largeur);
        ajouterCases(hauteur, largeur);
        demanderActionEnTete();



    }

    private void initialiserColonnes(int largeur){

        colonnesDeCases = new ArrayList<>(largeur);

    }

    private void ajouterEnTetes(int largeur){

        for(int i = 0; i < largeur; i++){

            VEntete entete = new VEntete( super.getContext(), i);
            this.addView(entete , getMiseEnPageEntete(i));
            installerListenerEntete(entete, i);
            entetes.add(entete);

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



        Spec specRangee = GridLayout.spec(rangee, 3.0f);
        Spec specColonne = GridLayout.spec(colonne, 1.0f);

        mesParams.columnSpec = specColonne;
        mesParams.rowSpec = specRangee;


        return  mesParams;

    }

    private void ajouterCases(int hauteur, int largeur){



        for (int i = 0; i < largeur ; i++){

            for (int j=hauteur; j> 0 ; j--){


                VCase leCase = new VCase(super.getContext(),j-(2*(j-hauteur))- hauteur, i);

                addView(leCase, getMiseEnPageCase(j, i ));

                colonnesDeCases.add(leCase);
               lesCases[j-(2*(j-hauteur))- hauteur][i] = leCase;

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
        


        Spec specRangee = GridLayout.spec(rangee +1, 1.0f);
        Spec specColonne = GridLayout.spec(colonne, 1.0f);
        mesParams.columnSpec = specColonne;
        mesParams.rowSpec = specRangee;

        return  mesParams;

    }

    private void initialiserTableauDeCases(int hauteur, int largeur){

        lesCases = new VCase[hauteur][largeur];

    }



    private void demanderActionEnTete(){

       action =  ControleurAction.demanderAction(GCommande.JOUER_COUP_ICI);




    }

    private void installerListenerEntete(final VEntete entete, final int colonne){

        entete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               action.setArguments(colonne);
               action.executerDesQuePossible();



            }
        });


    }

    void afficherJetons(MGrille grille){

        for(int i = 0; i <grille.getColonnes().size(); i++){

            for (int j = 0; j < grille.getColonnes().get(i).getJetons().size(); j++) {

                afficherJeton(i, j, grille.getColonnes().get(i).getJetons().get(j));
            }
        }





    }

    private void afficherJeton(int colonne, int rangee, GCouleur jeton){

        lesCases[rangee][colonne].afficherJeton(jeton);

    }


}
