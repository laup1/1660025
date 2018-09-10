package ca.cours5b5.laurenperez.Serialisation;

import ca.cours5b5.laurenperez.exceptions.ErreurDeConstruction;

public class Construction {

    public static <T extends Constructible> T construir(Class<T> classeInstantier) throws ErreurDeConstruction{

    }

    private static <T extends Constructible> T finaliserConstruction(Class<T> classeAInstantier, Object obj){

    }
}
