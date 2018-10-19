package ca.cours5b5.laurenperez.controleurs;

import android.util.ArrayMap;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.laurenperez.controleurs.interfaces.Fournisseur;
import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.laurenperez.global.GCommande;
import ca.cours5b5.laurenperez.modeles.Modele;

public class ControleurAction {

    private static Map<GCommande, Action> actions;
    private static List<Action> fileAttenteExecution;

    static{

        actions = new HashMap<>();
        fileAttenteExecution = new ArrayList<>();

        for (GCommande commande : GCommande.values() ) {

            actions.put(commande, new Action());

        }



    }

    public static Action demanderAction(GCommande commande){

        return actions.get(commande);

    }

    public static void  fournirAction(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur){

        enregistrerFournisseur( fournisseur, commande, listenerFournisseur );
        executerActionExecutables();

    }

    static void executerDesQuePossible(Action action){
        Log.d("atelier07", ControleurAction.class.getSimpleName() + "::executerDesQuePossible");
        ajouterActionEnFileAttente(action);
        executerActionExecutables();


    }

    private static void executerActionExecutables(){

        for(Action actionExecuter : fileAttenteExecution){

            if(siActionExecutable(actionExecuter)) {

                fileAttenteExecution.remove(actionExecuter);
                executerMaintenant(actionExecuter);
                lancerObservationSiApplicable(actionExecuter);


            }
        }


    }
    static  boolean siActionExecutable(Action action){

        boolean executable = false;

        if(action.getListenerFournisseur() != null){
            executable = true;
        }

        return executable;


    }

    private static synchronized void executerMaintenant(Action action){
//verificar
        action.getListenerFournisseur().executer(action.getArgs());
        Log.d("atelier07", ControleurAction.class.getSimpleName() + "::executerMaintenant");



    }
    //verificar aqui

    private static void lancerObservationSiApplicable(Action action){

        if(action.getFournisseur() instanceof Modele){

            ControleurObservation.lancerObservation((Modele) action.getFournisseur());

        }

    }

    private static void enregistrerFournisseur(Fournisseur fournisseur,GCommande commande, ListenerFournisseur listenerFournisseur){


        Action action = demanderAction(commande);
        action.setFournisseur(fournisseur);
        action.setListenerFournisseur(listenerFournisseur);

    }

    private static void ajouterActionEnFileAttente(Action action){

        Action clone = action.cloner();
        fileAttenteExecution.add(clone);

    }

}