package ca.cours5b5.laurenperez.vues;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.activites.AMenuPrincipal;
import ca.cours5b5.laurenperez.controleurs.Action;
import ca.cours5b5.laurenperez.controleurs.ControleurAction;
import ca.cours5b5.laurenperez.global.GCommande;
import ca.cours5b5.laurenperez.usagers.UsagerCourant;

import static ca.cours5b5.laurenperez.global.GConstantes.CODE_CONNEXION_FIREBASE;


public class VMenuPrincipal extends Vue {


    private Action actionPartieReseau1;

    public VMenuPrincipal(Context context) {
        super(context);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Button boutonParametres;
    private Action actionParametres;

    private Button boutonPartie;
    private Action actionPartie;

    private Button boutonPartieReseau;
    private Action actionPartieReseau;

    private Button boutonConnexion;
    private Action actionConnexion;
    private Action actionDeconnexion;



    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        recupererControles();

        demanderActions();

        installerListeners();

        ajusterTexteConnexionDeconnexion();



        if(!UsagerCourant.siUsagerConnecte()){

            Toast.makeText(getContext(),R.string.messageConnection, Toast.LENGTH_LONG).show();



        }

    }


    private void recupererControles() {

        boutonParametres = findViewById(R.id.bouton_parametres);

        boutonPartie = findViewById(R.id.bouton_partie);

        boutonPartieReseau = findViewById(R.id.bouton_partie_reseau);

        boutonConnexion = findViewById(R.id.bouton_connexion);



    }

    private void demanderActions() {

        actionParametres = ControleurAction.demanderAction(GCommande.OUVRIR_MENU_PARAMETRES);

        actionPartie = ControleurAction.demanderAction(GCommande.DEMARRER_PARTIE);

        actionPartieReseau = ControleurAction.demanderAction(GCommande.JOINDRE_OU_CREER_PARTIE_RESEAU);

        actionConnexion = ControleurAction.demanderAction(GCommande.CONNEXION);

        actionDeconnexion = ControleurAction.demanderAction(GCommande.DECONNEXION);


    }


    private void installerListeners() {

            boutonPartie.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(UsagerCourant.siUsagerConnecte()) {
                        actionPartie.executerDesQuePossible();
                    }else {
                        Toast.makeText(getContext(),R.string.messageConnection, Toast.LENGTH_LONG).show();

                        actionConnexion.executerDesQuePossible();
                       boutonConnexion.setText(R.string.deconnexion);



                            if (UsagerCourant.siUsagerConnecte()) {
                               // boutonConnexion.setText(R.string.deconnexion);
                                actionPartie.executerDesQuePossible();


                            }
                        }

                    }

            });

            boutonParametres.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                        actionParametres.executerDesQuePossible();

                }
            });

            boutonPartieReseau.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(UsagerCourant.siUsagerConnecte()) {

                        actionPartieReseau.executerDesQuePossible();

                    }else{
                        Toast.makeText(getContext(),R.string.messageConnection, Toast.LENGTH_LONG).show();
                        actionConnexion.executerDesQuePossible();
                        actionConnexion.notify();
                        boutonConnexion.setText(R.string.deconnexion);


                        if(UsagerCourant.siUsagerConnecte()) {
                            actionPartieReseau.executerDesQuePossible();
                        }



                    }
                }
            });





        boutonConnexion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!UsagerCourant.siUsagerConnecte()){

                    actionConnexion.executerDesQuePossible();
                    boutonConnexion.setText(R.string.deconnexion);


                }else{

                    actionDeconnexion.executerDesQuePossible();
                    boutonConnexion.setText(R.string.connexion);


                }

            }
        });
    }


    private void ajusterTexteConnexionDeconnexion() {
        if(UsagerCourant.siUsagerConnecte()){

            boutonConnexion.setText(R.string.deconnexion);

        }else{

            boutonConnexion.setText(R.string.connexion);
            //Toast.makeText(getContext(),R.string.messageConnection, Toast.LENGTH_LONG).show();


        }
    }



}
