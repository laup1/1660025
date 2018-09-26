package ca.cours5b5.laurenperez.modeles;

import java.util.Map;

import ca.cours5b5.laurenperez.Serialisation.AttributSerialisable;
import ca.cours5b5.laurenperez.exceptions.ErreurDeSerialisation;

public class MPartie extends Modele {

    @AttributSerialisable
    public MParametresPartie parametres;
    private final String __parametres = "parametres";

    public MPartie(MParametresPartie parametres){

        this.parametres = parametres;


    }

    public MParametresPartie getParametres() {
        return parametres;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurDeSerialisation{
        //RIEN CODER ICI POUR L'INSTANT
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurDeSerialisation {

        //RIEN CODER ICI POUR L'INSTANT
        return null;
    }
}
