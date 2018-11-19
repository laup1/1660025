package ca.cours5b5.laurenperez.proxy;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.laurenperez.controleurs.Action;
import ca.cours5b5.laurenperez.controleurs.ControleurAction;
import ca.cours5b5.laurenperez.controleurs.interfaces.Fournisseur;
import ca.cours5b5.laurenperez.global.GCommande;
import ca.cours5b5.laurenperez.global.GConstantes;

public class ProxyListe  extends Proxy implements Fournisseur{

    private ChildEventListener childEventListener;
    private Query requete;
    private Action actionNouvelItem = new Action();
    private List<DatabaseReference> noeudsAjoutes;

    public ProxyListe(String cheminServeur) {
        super(cheminServeur);
        noeudsAjoutes = new ArrayList<>();
        getRequete();

    }

    public void setActionNouvelItem(GCommande commande){
        actionNouvelItem = ControleurAction.demanderAction(commande);
    }

    public void ajouterValeur(Object valeur) {
        /*
         * Créer un sous-noeud avec push()
         * Mémoriser le noeud ajouté
         * Ajouter la valeur avec setValue()
         */
        DatabaseReference nouveauNoeud = this.noeudServeur.push();
        noeudsAjoutes.add(nouveauNoeud);
        nouveauNoeud.setValue(valeur);
    }

    @Override
    public void connecterAuServeur() {
        /*
         * Créer le listener
         * sauvegarder la requête
         * ajouter le listener
         */
        super.connecterAuServeur();
        creerListener();

        requete = noeudServeur;
        requete.addChildEventListener(childEventListener);
    }

    private void creerListener(){
        // TODO remplir les methode du listener
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Object valeurAjoutee = dataSnapshot.getValue();
                actionNouvelItem.setArguments(valeurAjoutee);
                actionNouvelItem.executerDesQuePossible();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }

    protected Query getRequete(){
        /*
         * On veut trier par clé et limiter à un nombre max (utiliser une constante)
         */
        requete.orderByKey().limitToLast(GConstantes.NOMBRE_DE_VALEURS_A_CHARGER_DU_DERVEUR_PAR_DEFAUR);
        return requete;
    }

    @Override
    public void deconnecterDuServeur() {
        /*
         * retirer le listener
         * oublier les noeuds ajoutés
         * déconnecter via super
         */
        requete.removeEventListener(childEventListener);
        noeudsAjoutes.clear();
        super.deconnecterDuServeur();
    }

    @Override
    public void detruireValeurs() {
        noeudServeur.removeValue();
        noeudsAjoutes.clear();
    }
}