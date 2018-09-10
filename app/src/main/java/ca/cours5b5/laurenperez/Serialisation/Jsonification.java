package ca.cours5b5.laurenperez.Serialisation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Jsonification {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T extends Serialisable> T aPartirJson(Class<T> classeAImplementer, String json){

    }

    public static String enJson(Serialisable obj){

    }

    public static <T extends Serialisable> T aPartirJson(Serialisable obj, String json){

    }

    public static <T extends Serialisable> T aPartirObjetJson(Serialisable obj, Map<String, Object> objetJson){

    }

}
