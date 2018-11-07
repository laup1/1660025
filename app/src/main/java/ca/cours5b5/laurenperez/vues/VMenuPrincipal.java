package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.controleurs.Action;
import ca.cours5b5.laurenperez.controleurs.ControleurAction;
import ca.cours5b5.laurenperez.global.GCommande;
import ca.cours5b5.laurenperez.usagers.UsagerCourant;


public class VMenuPrincipal extends Vue {


    public static VMenuPrincipal viewMenu;
    private Button boutonParametres;
    private Action actionParametres;

    private Button boutonPartie;
    private Action actionPartie;
    private Action actionConnection;
    private Button boutonConnection;
    private Action actionDeconnection;

    public VMenuPrincipal(Context context) {
        super(context);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        viewMenu = findViewById(R.id.menu);

        recupererControles();

        demanderActions();

        installerListeners();
        determinerString();



    }


    private void recupererControles() {

        boutonParametres = findViewById(R.id.bouton_parametres);

        boutonPartie = findViewById(R.id.bouton_partie);

        boutonConnection = findViewById(R.id.connexion);

    }

    private void demanderActions() {

        actionParametres = ControleurAction.demanderAction(GCommande.OUVRIR_MENU_PARAMETRES);

        actionPartie = ControleurAction.demanderAction(GCommande.DEMARRER_PARTIE);
        actionConnection = ControleurAction.demanderAction(GCommande.SECONNECTER);
        actionDeconnection = ControleurAction.demanderAction(GCommande.SE_DECONNECTER);


    }


    private void installerListeners() {

        installerListenerParametres();

        installerListenerPartie();

        determinerString();





    }

    public void determinerString(){
        if(UsagerCourant.siUsagerConnecte()){ //true
            boutonConnection.setText(R.string.out);
            installerListenerDeconnection();
        } else{
            installerListenerConnecion();
            boutonConnection.setText(R.string.connexion);
        }
    }

    private void installerListenerPartie() {

        boutonPartie.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPartie.executerDesQuePossible();
            }
        });

    }

    private void installerListenerParametres() {

        boutonParametres.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionParametres.executerDesQuePossible();
            }
        });

    }
    private void installerListenerDeconnection() {



        boutonConnection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionDeconnection.executerDesQuePossible();
            }
        });

    }


    private void installerListenerConnecion() {

        boutonConnection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionConnection.executerDesQuePossible();
            }
        });

    }

}
