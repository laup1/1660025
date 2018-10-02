package ca.cours5b5.laurenperez.modeles;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import ca.cours5b5.laurenperez.Serialisation.AttributSerialisable;
import ca.cours5b5.laurenperez.exceptions.ErreurDeSerialisation;
import ca.cours5b5.laurenperez.global.GConstantes;

public class MParametres extends Modele {

    public static MParametres instance = new MParametres();

   @AttributSerialisable
   public MParametresPartie parametresPartie;
   private String __parametresPartie = "parametresPartie";

    private List<Integer> choixHauteur;
    private List<Integer> choixLargeur;
    private List<Integer> choixPourGagner;

    public MParametres(){


      parametresPartie = new MParametresPartie();
      genererListesDeChoix();
    }

    public List<Integer> getChoixHauteur(){
        return choixHauteur;
    }

    public List<Integer> getChoixLargeur(){
        return choixLargeur;
    }

    public List<Integer> getChoixPourGagner(){
        return choixPourGagner;
    }

    public MParametresPartie getParametresPartie() {

        return parametresPartie;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurDeSerialisation{
    //charge les données du modèle à partir d'une sauvegarde en objetJson
        parametresPartie.aPartirObjetJson(objetJson);




    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurDeSerialisation{
    //retoure une sauvegarde des données du modèle


    return  parametresPartie.enObjetJson();


    }

    private List<Integer> genererListeChoix(int min, int max){

        List<Integer> listChoix = new ArrayList<Integer>();
        for ( ; min <= max; min++){

            listChoix.add(min);

        }
        return listChoix;


    }
    private void genererListesDeChoix(){
        choixHauteur = genererChoixHauteur();
        choixLargeur = genererChoixLargeur();
        choixPourGagner = genererChoixPourGagner();
    }

    public List<Integer> genererChoixHauteur(){


       return genererListeChoix(GConstantes.MIN_HAUTEUR,GConstantes.MAX_HAUTEUR);



    }
    public List<Integer> genererChoixLargeur(){

        return  genererListeChoix(GConstantes.MIN_LARGEUR,GConstantes.MAX_LARGEUR);



    }
    public List<Integer> genererChoixPourGagner(){


       return genererListeChoix(GConstantes.MIN_POURGAGNER, GConstantes.MAX_POURGAGNER);



    }




}
