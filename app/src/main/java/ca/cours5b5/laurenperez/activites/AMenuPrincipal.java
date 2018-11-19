package ca.cours5b5.laurenperez.activites;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.controleurs.ControleurAction;
import ca.cours5b5.laurenperez.controleurs.interfaces.Fournisseur;
import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.laurenperez.global.GCommande;
import ca.cours5b5.laurenperez.global.GConstantes;
import ca.cours5b5.laurenperez.modeles.MPartieReseau;
import ca.cours5b5.laurenperez.usagers.UsagerCourant;
import ca.cours5b5.laurenperez.vues.VMenuPrincipal;

public class AMenuPrincipal extends Activite implements Fournisseur {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        fournirActions();

    }

    private void fournirActions() {

        fournirActionOuvrirMenuParametres();

        fournirActionDemarrerPartie();

        fournirActionConnection();

        fournirActionDeconnection();
        fournirActionJoindreOuCreerPartieReseau();
    }

    private void fournirActionOuvrirMenuParametres() {

        ControleurAction.fournirAction(this,
                GCommande.OUVRIR_MENU_PARAMETRES,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionParametres();


                    }
                });
    }

    private void fournirActionConnection(){


        ControleurAction.fournirAction(this,
                GCommande.SECONNECTER,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionConnection();
                        VMenuPrincipal.viewMenu.determinerString();


                    }
                });
                }


    private void fournirActionDeconnection(){


        ControleurAction.fournirAction(this,
                GCommande.SE_DECONNECTER,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionDeconnection();
                        VMenuPrincipal.viewMenu.determinerString();


                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        //super.onActivityResult(requestCode, resultCode, data);
        VMenuPrincipal.viewMenu.determinerString();
        if(requestCode == GConstantes.CODE_CONNEXION){
            if (resultCode == RESULT_OK){
                Log.d("atelier11", "connexion réussie" );
            }else{

                Log.d("atelier11", "connexion échoué" );


            }


        }
    }

    private void fournirActionDemarrerPartie() {

        ControleurAction.fournirAction(this,
                GCommande.DEMARRER_PARTIE,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionPartie();

                    }
                });
    }

    private void transitionParametres(){

        Intent intentionParametres = new Intent(this, AParametres.class);
        startActivity(intentionParametres);

    }

    private void transitionDeconnection(){

        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {

                        Log.d("atelier11","Déconnecté");
                        VMenuPrincipal.viewMenu.determinerString();


                    }
                });

    }



    private void transitionPartie(){

        Intent intentionParametres = new Intent(this, APartie.class);
        startActivity(intentionParametres);

    }

    private void transitionConnection(){

        List<AuthUI.IdpConfig> fournisseursDeConnexion = new ArrayList<>();

        fournisseursDeConnexion.add(new AuthUI.IdpConfig.GoogleBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.EmailBuilder().build());
        fournisseursDeConnexion.add(new AuthUI.IdpConfig.PhoneBuilder().build());

        Intent intentionConnexion = AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(fournisseursDeConnexion).build();

        this.startActivityForResult(intentionConnexion, GConstantes.CODE_CONNEXION);




    }

    private void fournirActionJoindreOuCreerPartieReseau(){

        ControleurAction.fournirAction(this,
                GCommande.JOINDRE_OU_CREER_PARTIE_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        transitionPartieReseau();
                    }
                });



    }

    private void transitionPartieReseau(){


        Intent monIntention = new Intent(this, APartieReseau.class);
        monIntention.putExtra(MPartieReseau.class.getSimpleName(), GConstantes.FIXME_JSON_PARTIE_RESEAU);
        this.startActivity(monIntention);

    }



}
