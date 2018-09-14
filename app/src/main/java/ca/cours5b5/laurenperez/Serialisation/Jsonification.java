package ca.cours5b5.laurenperez.Serialisation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class Jsonification {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

   public static Map<String, Object> enObjetJson(String json){

       //obtenir un objetJson a partir d'un chaine

       Map<String, Object> objetJson = gson.fromJson(json, Map.class);

       return objetJson;
   }

   public static String enChaine(Map<String, Object> objetJson){

       //obtenir un chaine a partir d'un objet json

       String chaineJson = gson.toJson(objetJson);

       return chaineJson;
   }
}
