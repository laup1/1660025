package ca.cours5b5.laurenperez.controleurs;

import android.util.Log;

import ca.cours5b5.laurenperez.controleurs.interfaces.Fournisseur;
import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerObservateur;

public class Action {


    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public ListenerFournisseur getListenerFournisseur() {
        return listenerFournisseur;
    }

    Fournisseur fournisseur;

    ListenerFournisseur listenerFournisseur;

    Object[] args;

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public void setListenerFournisseur(ListenerFournisseur listenerFournisseur) {
        this.listenerFournisseur = listenerFournisseur;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }





    public void setArguments(Object... args){

        this.args = args;
           // if (args instanceof Fournisseur) {
             //   fournisseur = (Fournisseur) args;
               // this.args[0] = args;
            //} else if(args instanceof ListenerFournisseur) {
              //  listenerFournisseur = (ListenerFournisseur) args;
               // this.args[1] = args;
            //} else {
              //  this.args[2] = args;
            //}


    }



    public void executerDesQuePossible(){

        ControleurAction.executerDesQuePossible(this);
        Log.d("atelier07", Action.class.getSimpleName() + "::executerDesQuePossible");

    }

    Action cloner(){

        Action clone = new Action();

        if(args != null){


            clone.setFournisseur(fournisseur);
            clone.setListenerFournisseur(listenerFournisseur);

           clone.setArgs(args.clone());
        }

        return clone;
    }
}
