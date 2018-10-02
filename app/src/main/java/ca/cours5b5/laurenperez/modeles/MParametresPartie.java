package ca.cours5b5.laurenperez.modeles;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.laurenperez.Serialisation.AttributSerialisable;
import ca.cours5b5.laurenperez.exceptions.ErreurDeSerialisation;
import ca.cours5b5.laurenperez.global.GConstantes;

public class MParametresPartie extends Modele {

    @AttributSerialisable
    public Integer hauteur;
    private final String __hauteur = "hauteur";

    @AttributSerialisable
    public Integer largeur;
    private final String __largeur = "largeur";

    @AttributSerialisable
    public Integer pourGagner;
    private final String __pourGagner = "pourGagner";

    public static MParametresPartie aPartirMParametres(MParametres mParametres){

         MParametresPartie instanceMParametresPartie = mParametres.getParametresPartie().cloner();


         return instanceMParametresPartie;
    }

    public MParametresPartie cloner(){

        MParametresPartie partieClone = new MParametresPartie(MParametres.instance.getParametresPartie().getHauteur(), MParametres.instance.getParametresPartie().getLargeur(), MParametres.instance.getParametresPartie().getPourGagner());

        return partieClone;

    }
    public MParametresPartie(){



            hauteur = GConstantes.DEFAULT_HAUTEUR;
            largeur = GConstantes.DEFAULT_LARGEUR;
            pourGagner = GConstantes.DEFAULT_POURGAGNER;






    }
    public MParametresPartie(int hauteur, int largeur, int pourGagner){

        this.hauteur = hauteur;
        this.largeur = largeur;
        this.pourGagner = pourGagner;


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

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setPourGagner(int pourGagner) {
        this.pourGagner = pourGagner;
    }


    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurDeSerialisation{

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
    public Map<String, Object> enObjetJson() throws ErreurDeSerialisation{

        Map<String, Object> objetJson = new HashMap<>();
        objetJson.put(__hauteur, hauteur.toString());
        objetJson.put(__largeur, largeur.toString());
        objetJson.put(__pourGagner, pourGagner.toString());
        return objetJson;

    }
}
