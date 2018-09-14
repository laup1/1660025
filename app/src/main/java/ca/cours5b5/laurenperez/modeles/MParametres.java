package ca.cours5b5.laurenperez.modeles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.laurenperez.Serialisation.AttributSerialisable;
import ca.cours5b5.laurenperez.Serialisation.Jsonification;
import ca.cours5b5.laurenperez.global.GConstantes;


public class MParametres extends Modele {

    public static MParametres instance = new MParametres();

    @AttributSerialisable
    public Integer hauteur = GConstantes.DEFAULT_HAUTEUR;
    private final String __hauteur = "hauteur";

    @AttributSerialisable
    public Integer largeur = GConstantes.DEFAULT_LARGEUR;
    private final String __largeur = "largeur";

    @AttributSerialisable
    public Integer pourGagner = GConstantes.DEFAULT_POURGAGNER;
    private final String __pourGagner = "pourGagner";

    public MParametres(){

       getChoixHauteur();
       getChoixLargeur();
       getChoixLargeur();
    }



    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {
    //charge les données du modèle à partir d'une sauvegarde en objetJson

        for (Map.Entry<String, Object> entry : objetJson.entrySet()) {

            String cle = entry.getKey();
            Object valeur = entry.getValue();

            if(cle.equals(__hauteur)) {
                hauteur = Integer.valueOf((String) valeur);
            } else if(cle.equals(__largeur)){
                largeur = Integer.valueOf((String) valeur);
            }else{
                pourGagner = Integer.valueOf((String) valeur);
            }


        }



    }

    @Override
    public Map<String, Object> enObjetJson() {
    //retoure une sauvegarde des données du modèle

        Map<String, Object> objetJson = new HashMap<>();
        objetJson.put(__hauteur, hauteur.toString());
        objetJson.put(__largeur, largeur.toString());
        objetJson.put(__pourGagner, pourGagner.toString());
        return objetJson;




    }




    private List<Integer> genererListeChoix(int min, int max){

        List<Integer> listChoix = new ArrayList<Integer>();
        for ( ; min <= max; min++){

            listChoix.add(min);

        }
        return listChoix;


    }

    public List<Integer> getChoixHauteur(){

       return genererListeChoix(GConstantes.MIN_HAUTEUR,GConstantes.MAX_HAUTEUR);



    }
    public List<Integer> getChoixLargeur(){

        return  genererListeChoix(GConstantes.MIN_LARGEUR,GConstantes.MAX_LARGEUR);


    }
    public List<Integer> getChoixPourGagner(){

       return genererListeChoix(GConstantes.MIN_POURGAGNER, GConstantes.MAX_POURGAGNER);


    }




}
