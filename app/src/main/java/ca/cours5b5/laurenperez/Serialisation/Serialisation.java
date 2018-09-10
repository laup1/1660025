package ca.cours5b5.laurenperez.Serialisation;

import java.lang.reflect.Field;
import java.util.Map;

import ca.cours5b5.laurenperez.exceptions.ErreurDeSerialisation;

public class Serialisation {

    public static Map<String, Object> serialiser(Serialisable obj) throws ErreurDeSerialisation{

    }
    public static void deserialiser(Serialisable obj, Map<String, Object> objetJson)  throws ErreurDeSerialisation{

    }
    private static void serialiserAttributs(Map<String, Object> objetJson, Serialisable obj){

    }
    private static boolean siAttributSerialisable(Field attribut){

    }
    private static void serialiserAttribut(Map<String, Object> objetJon, Serialisable obj, Field attribut){

    }
    private static Object serialiserValeur(Class type, Object valeur){

    }
    private static  void deserialiserAttributs(Serialisable obj, Map<String, Object> objetJson){

    }
    private static  void deserialiserAttributs(Serialisable obj, String nomAttribut, Object valeurAttribut){

    }
    private static  void deserialiserAttributs(Serialisable obj,Field attribut, Object valeurAttribut){

    }

}
