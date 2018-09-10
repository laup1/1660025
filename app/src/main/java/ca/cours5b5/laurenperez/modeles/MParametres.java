package ca.cours5b5.laurenperez.modeles;

import android.widget.ArrayAdapter;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.Serialisation.AttributSerialisable;
import ca.cours5b5.laurenperez.global.GConstantes;

public class MParametres extends Modele {

    public static MParametres instance;

    @AttributSerialisable
    public Integer hauteur;

    @AttributSerialisable
    public Integer largeur;

    @AttributSerialisable
    public Integer pourGagner;



    private List<Integer> choixHauteur;
    private List<Integer> choixLargeur;
    private List<Integer> choixPourGagner;

    public MParametres(){

        genererListesChoix();



    }

    public List<Integer> getChoixHauteur() {
        return choixHauteur;
    }

    public List<Integer> getChoixLargeur() {
        return choixLargeur;
    }

    public List<Integer> getChoixPourGagner() {
        return choixPourGagner;
    }

    public Integer getHauteur() {
        return hauteur;
    }

    public Integer getLargeur() {
        return largeur;
    }

    public Integer getPourGagner() {
        return pourGagner;
    }

    public void setHauteur(Integer hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(Integer largeur) {
        this.largeur = largeur;
    }

    public void setPourGagner(Integer pourGagner) {
        this.pourGagner = pourGagner;
    }

    private void genererListesChoix(){

        genererListeChoixHauteur();
        genererListeChoixLargeur();
        genererListeChoixPourGagner();

    }

    private List<Integer> genererListeChoix(int min, int max){

        List<Integer> listChoix = new ArrayList<Integer>();
        for ( ; min <= max; min++){

            listChoix.add(min);

        }
        return listChoix;


    }

    private void genererListeChoixHauteur(){

       choixHauteur = genererListeChoix(GConstantes.MIN_HAUTEUR,GConstantes.MAX_HAUTEUR);



    }
    private void genererListeChoixLargeur(){

        choixLargeur = genererListeChoix(GConstantes.MIN_LARGEUR,GConstantes.MAX_LARGEUR);


    }
    private void genererListeChoixPourGagner(){

        choixPourGagner = genererListeChoix(GConstantes.MIN_POURGAGNER, GConstantes.MAX_POURGAGNER);


    }


}
