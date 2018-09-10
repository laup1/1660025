package ca.cours5b5.laurenperez.modeles;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Map;

import ca.cours5b5.laurenperez.activites.AParametres;

public abstract class Modele {

    public abstract void aPartirObjetJson(Map<String, Object> objetJson);

    public abstract Map<String, Object> enObjetJson();


}
