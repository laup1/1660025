package ca.cours5b5.laurenperez.usagers;

import com.google.firebase.auth.FirebaseAuth;

public class UsagerCourant {



    public static boolean siUsagerConnecte(){

        boolean connecte = false;

        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            connecte = true;
        }

        return connecte;
    }



    public static String getId(){

        String id;
        if(siUsagerConnecte()){

            FirebaseAuth auth = FirebaseAuth.getInstance();
            id = auth.getCurrentUser().getUid();

        }else{

            id = "id1234";

        }

       return id;

    }
}
